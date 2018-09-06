package com.codingapi.tm.manager.service;

import com.codingapi.tm.model.TxServer;
import com.codingapi.tm.model.TxState;

/**
 * create by lorne on 2017/11/11
 */
public interface MicroService {
    /**
     * 用于去注册中心获取tx-manager注册的机器
     */
    String  tmKey = "tx-manager";

    TxServer getServer();

    TxState getState();
}
