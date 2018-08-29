package com.codingapi.tx.netty.service;

/**
 * Created by lorne on 2017/6/30.
 */
public interface NettyService {
    /**
     * netty服务开启
     */
    void start();

    /**
     * netty服务关闭
     */
    void close();

    /***
     * 检查服务状态
     * @return
     */
    boolean checkState();

}
