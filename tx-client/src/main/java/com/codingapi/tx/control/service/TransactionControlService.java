package com.codingapi.tx.control.service;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;

/**
 * 事务控制服务
 * create by lorne on 2017/11/11
 */
public interface TransactionControlService {

    /**
     * 通知事务消息
     * @param ctx netty渠道上下文
     * @param resObj 回复内容
     * @param json
     */
    void notifyTransactionMsg(ChannelHandlerContext ctx, JSONObject resObj, String json);
}
