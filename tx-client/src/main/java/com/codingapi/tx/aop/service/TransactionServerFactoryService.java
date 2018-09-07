package com.codingapi.tx.aop.service;

import com.codingapi.tx.aop.bean.TxTransactionInfo;

/**
 * 事务执行工厂
 * Created by lorne on 2017/6/8.
 */
public interface TransactionServerFactoryService {
     /*
      *生产事务执行对象
      */
    TransactionServer createTransactionServer(TxTransactionInfo info) throws Throwable;
}
