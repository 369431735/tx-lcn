package com.codingapi.tx.aop.service.impl;

import com.codingapi.tx.annotation.TxTransaction;
import com.codingapi.tx.aop.bean.TxTransactionInfo;
import com.codingapi.tx.aop.bean.TxTransactionLocal;
import com.codingapi.tx.aop.service.AspectBeforeService;
import com.codingapi.tx.aop.service.TransactionServer;
import com.codingapi.tx.aop.service.TransactionServerFactoryService;
import com.codingapi.tx.model.TransactionInvocation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

/**
 * springCloud 启动事务后会最终调用到该类
 * Created by lorne on 2017/7/1.
 */
@Service
public class AspectBeforeServiceImpl implements AspectBeforeService {

    @Autowired
    private TransactionServerFactoryService transactionServerFactoryService;


    private Logger logger = LoggerFactory.getLogger(AspectBeforeServiceImpl.class);

    /**
     * 注解前置操作
     * @param groupId
     * @param point
     * @return
     * @throws Throwable
     */

    @Override
    public Object around(String groupId, ProceedingJoinPoint point) throws Throwable {

        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        //事务执行器
        Class<?> clazz = point.getTarget().getClass();
        Object[] args = point.getArgs();
        Method thisMethod = clazz.getMethod(method.getName(), method.getParameterTypes());
        //注解
        TxTransaction transaction = thisMethod.getAnnotation(TxTransaction.class);
       //当前线程 分布式事务远程调用控制对象
        TxTransactionLocal txTransactionLocal = TxTransactionLocal.current();

        logger.debug("around--> groupId-> " +groupId+",txTransactionLocal->"+txTransactionLocal);
        //事务调用器
        TransactionInvocation invocation = new TransactionInvocation(clazz, thisMethod.getName(), thisMethod.toString(), args, method.getParameterTypes());
        //分布式事务信息
        TxTransactionInfo info = new TxTransactionInfo(transaction,txTransactionLocal,invocation,groupId);
         //创建事务服务
        TransactionServer server = transactionServerFactoryService.createTransactionServer(info);
         //执行事务
        return server.execute(point, info);
    }
}
