package com.codingapi.tm.api.controller;

import com.codingapi.tm.api.service.ApiAdminService;
import com.codingapi.tm.api.service.ApiModelService;
import com.codingapi.tm.compensate.model.TxModel;
import com.codingapi.tm.model.ModelInfo;
import com.codingapi.tm.model.ModelName;
import com.codingapi.tm.model.TxState;
import com.lorne.core.framework.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 获取事务协调器信息
 * Created by lorne on 2017/7/1.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ApiAdminService apiAdminService;

    @Autowired
    private ApiModelService apiModelService;

    /***
     * 获取在线模块
     * @return
     */
    @RequestMapping(value = "/onlines", method = RequestMethod.GET)
    public List<ModelInfo> onlines() {
        return apiModelService.onlines();
    }

    /***
     * 协调服务器与客户端的通讯的状态
     * @return
     */
    @RequestMapping(value = "/setting", method = RequestMethod.GET)
    public TxState setting() {
        return apiAdminService.getState();
    }

    /***
     * 查询补偿事务永久存储数据
     * @return
     */
    @RequestMapping(value = "/json", method = RequestMethod.GET)
    public String json() {
        return apiAdminService.loadNotifyJson();
    }

    @RequestMapping(value = "/modelList", method = RequestMethod.GET)
    public List<ModelName> modelList() {
        return apiAdminService.modelList();
    }

    /***
     * 根据模块名查询补偿事务永久存储数据
     * @param model
     * @return
     */
    @RequestMapping(value = "/modelTimes", method = RequestMethod.GET)
    public List<String> modelTimes(@RequestParam("model") String model) {
        return apiAdminService.modelTimes(model);
    }

    /***
     * 根据路径名查询补偿事务永久存储数据
     * @param path
     * @return
     */
    @RequestMapping(value = "/modelInfos", method = RequestMethod.GET)
    public List<TxModel> modelInfos(@RequestParam("path") String path) {
        return apiAdminService.modelInfos(path);
    }

    /***
     *补偿数据
     * @param path
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "/compensate", method = RequestMethod.GET)
    public boolean compensate(@RequestParam("path") String path) throws ServiceException {
        return apiAdminService.compensate(path);
    }

    @RequestMapping(value = "/delCompensate", method = RequestMethod.GET)
    public boolean delCompensate(@RequestParam("path") String path) throws ServiceException {
        return apiAdminService.delCompensate(path);
    }

    @RequestMapping(value = "/hasCompensate", method = RequestMethod.GET)
    public boolean hasCompensate() throws ServiceException {
        return apiAdminService.hasCompensate();
    }
}
