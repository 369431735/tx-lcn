package com.codingapi.tx.aop.service.impl;

import com.codingapi.tx.aop.bean.TxTransactionInfo;
import com.codingapi.tx.aop.service.TransactionServer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Service;

/**
 * Created by lorne on 2017/6/8.
 */
@Service(value = "txDefaultTransactionServer")
public class TxDefaultTransactionServerImpl implements TransactionServer {

    /**
     * @Description:  默认执行事务
     * @author      lixing
     * @param point 切面信息
     * @param info  分布式事务信息
     * @return      java.lang.Object
     * @exception
     * @date        2018/9/6 15:31
     */

    @Override
    public Object execute(ProceedingJoinPoint point, TxTransactionInfo info) throws Throwable {
        return point.proceed();
    }
}
