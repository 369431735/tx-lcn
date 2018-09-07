package com.codingapi.tm.api.service.impl;

import com.codingapi.tm.api.service.ApiAdminService;
import com.codingapi.tm.compensate.model.TxModel;
import com.codingapi.tm.compensate.service.CompensateService;
import com.codingapi.tm.manager.service.MicroService;
import com.codingapi.tm.model.ModelName;
import com.codingapi.tm.model.TxState;
import com.codingapi.tm.redis.service.RedisServerService;
import com.lorne.core.framework.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by lorne on 2017/11/12
 */
@Service
public class ApiAdminServiceImpl implements ApiAdminService {


    @Autowired
    private MicroService eurekaService;

    @Autowired
    private RedisServerService redisServerService;

    @Autowired
    private CompensateService compensateService;

    @Override
    public TxState getState() {
        return eurekaService.getState();
    }
    /***
     * 查询补偿事务永久存储数据
     * @return
     */
    @Override
    public String loadNotifyJson() {
        return redisServerService.loadNotifyJson();
    }

    @Override
    public List<ModelName> modelList() {
        return compensateService.loadModelList();
    }

    /***
     * 根据模块名查询补偿事务永久存储数据
     * @param model
     * @return
     */
    @Override
    public List<String> modelTimes(String model) {
        return compensateService.loadCompensateTimes(model);
    }

    @Override
    public List<TxModel> modelInfos(String path) {
        return compensateService.loadCompensateByModelAndTime(path);
    }
    /**
      * @Description: 补偿数据
      * @author      lixing
      * @param path
      * @return      boolean
      * @exception
      * @date        2018/9/7 15:38
      */
    @Override
    public boolean compensate(String path) throws ServiceException {
        return compensateService.executeCompensate(path);
    }

    /**
     * 删除补偿数据
     * @param path
     * @return
     */
    @Override
    public boolean delCompensate(String path) {
        return compensateService.delCompensate(path);
    }
    /***
     * 查询是是否有需要补偿的数据
     * @return
     */
    @Override
    public boolean hasCompensate() {
        return compensateService.hasCompensate();
    }
}
