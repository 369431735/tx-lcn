package com.codingapi.tx.aop.service;

import com.codingapi.tx.aop.bean.TxTransactionInfo;
import org.aspectj.lang.ProceedingJoinPoint;


/**
 * 事务执行器
 * Created by lorne on 2017/6/8.
 */
public interface TransactionServer {


    // void execute();
 /**
  * @Description:  执行事务
  * @author      lixing
  * @param point 切面信息
  * @param info  分布式事务信息
  * @return      java.lang.Object
  * @exception
  * @date        2018/9/6 15:31
  */
    Object execute(ProceedingJoinPoint point, TxTransactionInfo info) throws Throwable;

}
