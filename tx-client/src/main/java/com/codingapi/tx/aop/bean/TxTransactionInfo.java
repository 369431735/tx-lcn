package com.codingapi.tx.aop.bean;

import com.codingapi.tx.annotation.TxTransaction;
import com.codingapi.tx.model.TransactionInvocation;


/**
 * 分布式事务信息
 * Created by lorne on 2017/6/8.
 */
public class TxTransactionInfo {

    /**
     * 注解
     */
    private TxTransaction transaction;

    /**
     * 本地事务对象
     */
    private TxTransactionLocal txTransactionLocal;

    /**
     * 事务组Id
     */
    private String txGroupId;

    /**
     * 分布式事务调用器
     */
    private TransactionInvocation invocation;


    public TxTransactionInfo(TxTransaction transaction, TxTransactionLocal txTransactionLocal, TransactionInvocation invocation, String txGroupId) {
        this.transaction = transaction;
        this.txTransactionLocal = txTransactionLocal;
        this.txGroupId = txGroupId;
        this.invocation = invocation;
    }


    public TxTransaction getTransaction() {
        return transaction;
    }

    public TxTransactionLocal getTxTransactionLocal() {
        return txTransactionLocal;
    }

    public String getTxGroupId() {
        return txGroupId;
    }

    public TransactionInvocation getInvocation() {
        return invocation;
    }

}
