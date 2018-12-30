package com.hxy.app.controller;


import com.hxy.activiti.entity.ExtendActModelEntity;
import com.hxy.activiti.service.ActModelerService;
import com.hxy.app.annotation.CurrentUser;
import com.hxy.app.annotation.LoginRequired;
import com.hxy.app.entity.ApiUserEntity;
import com.hxy.app.service.ApiActivitiService;
import com.hxy.app.service.ApiNoticeService;
import com.hxy.base.page.Page;
import com.hxy.base.utils.Result;
import com.hxy.base.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 类ApiLoginController的功能描述:
 * APP登录授权
 * @auther hxy
 * @date 2017-10-16 14:15:39
 */
@Controller
@RequestMapping("/app/activiti")
public class ApiActivitiController {

    @Autowired
    private ApiNoticeService apiNoticeService;
    @Autowired
    private ApiActivitiService apiActivitiService;

    /**
     * 我的待办条数
     */
    @RequestMapping(value = "myUpcomingCount",method = RequestMethod.POST)
    @LoginRequired
    @ResponseBody
    public Result myUpcomingCount(@CurrentUser ApiUserEntity apiUserEntity){
        //待办条数
        int myUpcomingCount = apiActivitiService.myUpcomingCount(apiUserEntity.getId());
        return Result.ok().put("myUpcomingCount", myUpcomingCount);
    }

    /**
     * 我的待办列表
     */
    @RequestMapping(value = "myUpcomingPage",method = RequestMethod.POST)
    @LoginRequired
    @ResponseBody
    public Result myUpcomingPage(@CurrentUser ApiUserEntity apiUserEntity,int pageNum,int pageSize,String code){
        Map<String,Object> params = new HashMap<>();
        params.put("code",code);
        Page<ExtendActModelEntity> page = apiActivitiService.myUpcomingPage(apiUserEntity.getId(),params, pageNum,pageSize);
        return Result.ok().put("myUpcomingPage",page);
    }

    /**
     * 我的已办列表
     */
    @RequestMapping(value = "myDonePage",method = RequestMethod.POST)
    @LoginRequired
    @ResponseBody
    public Result myDonePage(@CurrentUser ApiUserEntity apiUserEntity,int pageNum,int pageSize,String code){
        Map<String,Object> params = new HashMap<>();
        params.put("code",code);
        Page page = apiActivitiService.myDonePage(apiUserEntity.getId(),params, pageNum,pageSize);
        return Result.ok().put("myDonePage",page);
    }







}
