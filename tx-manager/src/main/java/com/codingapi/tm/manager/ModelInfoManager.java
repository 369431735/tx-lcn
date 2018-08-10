package com.codingapi.tm.manager;

import com.codingapi.tm.model.ModelInfo;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * create by lorne on 2017/11/13
 */
public class ModelInfoManager {


    private List<ModelInfo> modelInfos = new CopyOnWriteArrayList<ModelInfo>();

    private static ModelInfoManager manager = null;


    public static ModelInfoManager getInstance() {
        if (manager == null) {
            synchronized (ModelInfoManager.class) {
                if (manager == null) {
                    manager = new ModelInfoManager();
                }
            }
        }
        return manager;
    }

    /**
     * 根据渠道名移除模块
     * @param channelName
     */
    public void removeModelInfo(String channelName) {
        for (ModelInfo modelInfo : modelInfos) {
            if (channelName.equalsIgnoreCase(modelInfo.getChannelName())) {
                modelInfos.remove(modelInfo);
            }
        }
    }

    /***
     * 新增模块信息
     * @param minfo
     */
    public void addModelInfo(ModelInfo minfo) {
        for (ModelInfo modelInfo : modelInfos) {
            if (minfo.getChannelName().equalsIgnoreCase(modelInfo.getChannelName())) {
                return;
            }

            if (minfo.getIpAddress().equalsIgnoreCase(modelInfo.getIpAddress())) {
                return;
            }
        }
        modelInfos.add(minfo);
    }

    /***
     * 获取机器上所有模块
     * @return
     */
    public List<ModelInfo> getOnlines() {
        return modelInfos;
    }

    /***
     * 根据渠道名获取模块信息
     * @param channelName
     * @return
     */
    public ModelInfo getModelByChannelName(String channelName) {
        for (ModelInfo modelInfo : modelInfos) {
            if (channelName.equalsIgnoreCase(modelInfo.getChannelName())) {
                return modelInfo;
            }
        }
        return null;
    }

    /***
     * 根据模块名获取模块信息
     * @param model
     * @return
     */
    public ModelInfo getModelByModel(String model) {
        for (ModelInfo modelInfo : modelInfos) {
            if (model.equalsIgnoreCase(modelInfo.getModel())) {
                return modelInfo;
            }
        }
        return null;
    }
}
