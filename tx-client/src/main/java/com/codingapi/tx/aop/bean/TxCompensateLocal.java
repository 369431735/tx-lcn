package com.codingapi.tx.aop.bean;

/**
 * 分布式事务本地补偿
 * Created by lorne on 2017/6/5.
 */
public class TxCompensateLocal {

    private final static ThreadLocal<TxCompensateLocal> currentLocal = new InheritableThreadLocal<TxCompensateLocal>();
    /**
     * 组id
     */
    private String groupId;
    /***
     * 类型
     */
    private String type;
    /**
     * 开始状态
     */
    private int startState;


    public int getStartState() {
        return startState;
    }

    public void setStartState(int startState) {
        this.startState = startState;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static TxCompensateLocal current() {
        return currentLocal.get();
    }

    public static void setCurrent(TxCompensateLocal current) {
        currentLocal.set(current);
    }

}
