package com.codingapi.tm.compensate.service;

import com.codingapi.tm.compensate.model.TransactionCompensateMsg;
import com.codingapi.tm.compensate.model.TxModel;
import com.codingapi.tm.model.ModelName;
import com.codingapi.tm.netty.model.TxGroup;
import com.lorne.core.framework.exception.ServiceException;

import java.util.List;

/**
 * create by lorne on 2017/11/11
 */
public interface CompensateService {

    boolean saveCompensateMsg(TransactionCompensateMsg transactionCompensateMsg);

    List<ModelName> loadModelList();
    /***
     * 根据模块名查询补偿事务永久存储数据
     * @param model
     * @return
     */
    List<String> loadCompensateTimes(String model);

    List<TxModel> loadCompensateByModelAndTime(String path);

    void autoCompensate(String compensateKey, TransactionCompensateMsg transactionCompensateMsg);
    /**
      * @Description:补偿数据
      * @author      lixing
      * @param key
      * @return      boolean
      * @exception
      * @date        2018/9/7 15:38
      */
    boolean executeCompensate(String key) throws ServiceException;
    /**
      * @Description: 补偿请求，加载历史数据
      * @author      lixing
      * @param txGroup
      * @return      void
      * @exception
      * @date        2018/9/7 15:34
      */
    void reloadCompensate(TxGroup txGroup);
    /***
     * 查询是是否有需要补偿的数据
     * @return
     */
    boolean hasCompensate();
    /**
     * 删除补偿数据
     * @param path
     * @return
     */
    boolean delCompensate(String path);

    TxGroup  getCompensateByGroupId(String groupId);
}
