package com.codingapi.tx.netty.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.codingapi.tx.aop.bean.TxCompensateLocal;
import com.codingapi.tx.aop.bean.TxTransactionInfo;
import com.codingapi.tx.compensate.model.CompensateInfo;
import com.codingapi.tx.compensate.service.CompensateService;
import com.codingapi.tx.config.ConfigReader;
import com.codingapi.tx.framework.utils.SerializerUtils;
import com.codingapi.tx.framework.utils.SocketManager;
import com.codingapi.tx.listener.service.ModelNameService;
import com.codingapi.tx.model.Request;
import com.codingapi.tx.model.TxGroup;
import com.codingapi.tx.netty.service.MQTxManagerService;
import com.codingapi.tx.netty.service.TxManagerHttpRequestHelper;
import com.lorne.core.framework.utils.encode.Base64Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 分布式事务消息处理实现
 * Created by lorne on 2017/6/30.
 */
@Service
public class MQTxManagerServiceImpl implements MQTxManagerService {


    @Autowired
    private ModelNameService modelNameService;

    @Autowired
    private ConfigReader configReader;

    @Autowired
    private CompensateService compensateService;

    @Autowired
    private TxManagerHttpRequestHelper managerHelper;

    /**
     * 创建事务组
     * @param groupId 事务组id 当为补偿模式时将会自动获取补偿的groupId
     */
    @Override
    public void createTransactionGroup(String groupId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("g", groupId);
        Request request = new Request("cg", jsonObject.toString());
        SocketManager.getInstance().sendMsg(request);
    }
    /**
     * 添加事务组子对象
     * @param groupId   事务组id
     * @param taskId    任务Id
     * @param isGroup   是否合并到事务组 true合并 false不合并
     * @param methodStr   方法参数列表
     * @return  事务组TxGroup
     */
    @Override
    public TxGroup addTransactionGroup(String groupId, String taskId, boolean isGroup, String methodStr) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("g", groupId);
        jsonObject.put("t", taskId);
        jsonObject.put("ms", methodStr);
        jsonObject.put("s", isGroup ? 1 : 0);
        Request request = new Request("atg", jsonObject.toString());
        String json =  SocketManager.getInstance().sendMsg(request);
        return TxGroup.parser(json);
    }

    /**
     * 关闭事务组-进入事务提交第一阶段
     *
     * @param groupId   事务组id
     * @param state     提交或者回滚 1提交0回滚
     * @return 1 成功 0失败 -1 事务强制回滚
     */
    @Override
    public int closeTransactionGroup(final String groupId, final int state) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("g", groupId);
        jsonObject.put("s", state);
        Request request = new Request("ctg", jsonObject.toString());
        String json =  SocketManager.getInstance().sendMsg(request);
        try {
            return Integer.parseInt(json);
        }catch (Exception e){
            return 0;
        }
    }

    /***
     * 上传模块信息
     */
    @Override
    public void uploadModelInfo() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("m", modelNameService.getModelName());
        jsonObject.put("i", modelNameService.getIpAddress());
        jsonObject.put("u", modelNameService.getUniqueKey());
        Request request = new Request("umi", jsonObject.toString());
        String json = SocketManager.getInstance().sendMsg(request);
    }
    /**
     * 检查事务状态 通过netty管道
     * @param groupId   事务组id
     * @param taskId    任务id
     * @return  事务状态
     */
    @Override
    public int cleanNotifyTransaction(String groupId, String taskId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("g", groupId);
        jsonObject.put("t", taskId);
        Request request = new Request("ckg", jsonObject.toString());
        String json =  SocketManager.getInstance().sendMsg(request);
        try {
            return Integer.parseInt(json);
        }catch (Exception e){
            return -2;
        }
    }

    /**
     * 检查并清理事务数据
     * @param groupId   事务组id
     * @param waitTaskId    任务id
     * @return  事务状态
     */
    @Override
    public int cleanNotifyTransactionHttp(String groupId, String waitTaskId) {
        String url = configReader.getTxUrl() + "cleanNotifyTransactionHttp?groupId=" + groupId + "&taskId=" + waitTaskId;
        String clearRes = managerHelper.httpGet(url);
        if(clearRes==null){
            return -1;
        }
        return  clearRes.contains("true") ? 1 : 0;
    }

    /**
     * 获取TM服务地址
     * @return txServer
     */
    @Override
    public String httpGetServer() {
        String url = configReader.getTxUrl() + "getServer";
        return managerHelper.httpGet(url);
    }
    /**
     * 记录补偿事务数据到tm
     * @param groupId   事务组Id
     * @param time  执行时间
     * @param info  事务信息
     * @param startError 启动模块db执行异常
     */
    @Override
    public void sendCompensateMsg(String groupId, long time, TxTransactionInfo info,int startError) {

        String modelName = modelNameService.getModelName();
        String uniqueKey = modelNameService.getUniqueKey();
        String address = modelNameService.getIpAddress();


        byte[] serializers =  SerializerUtils.serializeTransactionInvocation(info.getInvocation());
        String data = Base64Utils.encode(serializers);

        String className = info.getInvocation().getTargetClazz().getName();
        String methodStr = info.getInvocation().getMethodStr();
        long currentTime = System.currentTimeMillis();


        CompensateInfo compensateInfo = new CompensateInfo(currentTime, modelName, uniqueKey, data, methodStr, className, groupId, address, time,startError);

        String json = managerHelper.httpPost(configReader.getTxUrl() + "sendCompensateMsg", compensateInfo.toParamsString());

        compensateInfo.setResJson(json);

        //记录本地日志
        compensateService.saveLocal(compensateInfo);

    }
}
