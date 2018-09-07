package com.codingapi.tm.api.service;

import com.codingapi.tm.compensate.model.TxModel;
import com.codingapi.tm.model.ModelName;
import com.codingapi.tm.model.TxState;
import com.lorne.core.framework.exception.ServiceException;

import java.util.List;

/**
 * create by lorne on 2017/11/12
 */
public interface ApiAdminService {

    TxState getState();
    /***
     * 查询补偿事务永久存储数据
     * @return
     */
    String loadNotifyJson();

    List<ModelName> modelList();

    /***
     * 根据模块名查询补偿事务永久存储数据
     * @param model
     * @return
     */
    List<String> modelTimes(String model);

    List<TxModel> modelInfos(String path);
    /**
      * @Description:补偿数据
      * @author      lixing
      * @param path
      * @return      boolean
      * @exception
      * @date        2018/9/7 15:38
      */
    boolean compensate(String path) throws ServiceException;

    boolean hasCompensate();

    boolean delCompensate(String path);

}
