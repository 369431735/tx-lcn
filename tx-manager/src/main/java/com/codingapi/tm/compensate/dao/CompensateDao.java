package com.codingapi.tm.compensate.dao;

import com.codingapi.tm.compensate.model.TransactionCompensateMsg;

import java.util.List;

/**
 * create by lorne on 2017/11/11
 */
public interface CompensateDao {
    /**
     * 储存补偿信息
     * @param transactionCompensateMsg
     * @return
     */
    String saveCompensateMsg(TransactionCompensateMsg transactionCompensateMsg);

    List<String> loadCompensateKeys();
    /***
     * 根据模块名查询补偿事务永久存储数据
     * @param model
     * @return
     */
    List<String> loadCompensateTimes(String model);

    List<String> loadCompensateByModelAndTime(String path);

    String getCompensate(String key);

    String getCompensateByGroupId(String groupId);
     /**
       * @Description: 删除补偿数据
       * @author      lixing
       * @param path
       * @return      void
       * @exception
       * @date        2018/9/7 15:39
       */
    void deleteCompensateByPath(String path);

    void deleteCompensateByKey(String key);
    /***
     * 查询是是否有需要补偿的数据
     * @return
     */
    boolean hasCompensate();
}
