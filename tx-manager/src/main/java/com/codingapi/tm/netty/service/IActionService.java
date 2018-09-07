package com.codingapi.tm.netty.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 事务操作（添加、创建、检查、关闭、补偿、获取负载模块信息、心跳、强制回滚事务组、 获取负载模块信息、添加负载模块信息、通知事务回调、上传模块信息）
 * create by lorne on 2017/11/11
 */
public interface IActionService {


    String execute(String channelAddress,String key,JSONObject params);

}
