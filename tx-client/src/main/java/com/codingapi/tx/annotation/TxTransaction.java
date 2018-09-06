package com.codingapi.tx.annotation;

import java.lang.annotation.*;

/**
 * 分布式事务主要注解
 * Created by lorne on 2017/6/26.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME) // 这种类型的Annotations将被JVM保留,所以他们能在运行时被JVM或其他使用反射机制的代码所读取和使用.
@Inherited //允许子类继承父类的注解。
@Documented  //表明这个注解应该被 javadoc工具记录. 默认情况下,javadoc是不包括注解的
public @interface TxTransaction {


    /**
     * 是否LCN事务发起方
     * @return true 是:是发起方 false 否:是参与方
     */
    boolean isStart() default false;


    /**
     * 回滚异常
     * @return
     */
    Class<? extends Throwable>[] rollbackFor() default {};


    /**
     * 不回滚异常
     * @return
     */
    Class<? extends Throwable>[] noRollbackFor() default {};

}
