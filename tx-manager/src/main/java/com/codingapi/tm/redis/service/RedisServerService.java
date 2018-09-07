package com.codingapi.tm.redis.service;

import com.codingapi.tm.netty.model.TxGroup;

import java.util.List;

/**
 * create by lorne on 2017/11/11
 */
public interface RedisServerService {
    /***
     * 查询补偿事务永久存储数据
     * @return
     */
    String loadNotifyJson();

    void saveTransaction(String key, String json);

    TxGroup getTxGroupByKey(String key);

    /**
     * 存储补偿数据
     * @param name
     * @param json
     */
    void saveCompensateMsg(String name, String json);

    List<String> getKeys(String key);

    List<String> getValuesByKeys(List<String> keys);

    String getValueByKey(String key);

    void deleteKey(String key);

    void saveLoadBalance(String groupName,String key,String data);


    String getLoadBalance(String groupName,String key);


}
