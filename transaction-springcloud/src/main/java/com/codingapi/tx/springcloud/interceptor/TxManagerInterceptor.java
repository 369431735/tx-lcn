package com.codingapi.tx.springcloud.interceptor;

import com.codingapi.tx.aop.service.AspectBeforeService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lorne on 2017/6/7.
 */

@Component
public class TxManagerInterceptor {
    /***
     * 前切面
     */
    @Autowired
    private AspectBeforeService aspectBeforeService;

    /**
     * 注解前置操作
     * @param point
     * @return
     * @throws Throwable
     */
    public Object around(ProceedingJoinPoint point) throws Throwable {
        String groupId = null;
        try {
            RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = requestAttributes == null ? null : ((ServletRequestAttributes) requestAttributes).getRequest();
            groupId = request == null ? null : request.getHeader("tx-group");
        }catch (Exception e){}
        return aspectBeforeService.around(groupId, point);
    }
}
