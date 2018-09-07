package com.codingapi.tx.aop.service;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 切面前置错误
 * Created by lorne on 2017/7/1.
 */
public interface AspectBeforeService {
    /**
     * 注解前置操作
     * @param groupId
     * @param point
     * @return
     * @throws Throwable
     */
    Object around(String groupId, ProceedingJoinPoint point) throws Throwable;
}
