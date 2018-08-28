package com.codingapi.tm.model;

/**
 * 模块信息
 * create by lorne on 2017/11/13
 */
public class ModelInfo {
    /**
     * 模块名
     */
    private String model;
    /***
     * ip地址
     */
    private String ipAddress;
    /**
     * 管道名
     */
    private String channelName;

    private String uniqueKey;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }
}
