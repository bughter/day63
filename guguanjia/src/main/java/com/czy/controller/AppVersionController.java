package com.czy.controller;

import com.czy.entity.AppVersion;
import com.czy.entity.Result;
import com.czy.service.AppVersionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * @author czy
 * @version 1.0.1
 * @company 东方标准
 * @date 2019/12/24 10:02
 * @description
 */
@RestController
@RequestMapping("manager/app")
public class AppVersionController {

    @Autowired
    AppVersionService appVersionService;

    @RequestMapping("index")
    public ModelAndView toIndex() {
        return new ModelAndView("/app/index");
    }

    @RequestMapping("toList")
    public PageInfo<AppVersion> toList(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
        return appVersionService.getPage(pageNum, pageSize);
    }


    @RequestMapping("toUpdate")
    public AppVersion toUpdate(Integer id) {
        AppVersion appVersion = appVersionService.selectByPrimaryKey(id);
        return appVersion;
    }

    @RequestMapping("toUpdatePage")
    public ModelAndView toUpdatePage(){
        return new ModelAndView("/app/update");
    }
    @RequestMapping("doUpdate")
    public Result doUpdate(@RequestBody AppVersion appVersion){
        Result result=new Result();
        appVersion.setUpdateDate(new Date());
        int i=appVersionService.updateByPrimaryKeySelective(appVersion);
        if(i>0){
            result.setSuccess(true);
            result.setMsg("更新成功！");
        }
        return result;
    }

    @RequestMapping("insert")
    public Result insert(@RequestBody AppVersion appVersion){
        Result result=new Result();
        appVersion.setUpdateDate(new Date());
        appVersion.setCreateBy("admin");
        appVersion.setCreateDate(new Date());
        appVersion.setDelFlag("0");
        int i=appVersionService.insertSelective(appVersion);
        if(i>0){
            result.setSuccess(true);
            result.setMsg("添加成功！");
        }
        return result;
    }


    @RequestMapping("delete")
    public Result delete(Integer id){
        AppVersion appVersion = appVersionService.selectByPrimaryKey(id);
        appVersion.setDelFlag("1");
        int i=appVersionService.updateByPrimaryKeySelective(appVersion);
        Result result=new Result();
//        int i=appVersionService.deleteByPrimaryKey(id);
        if(i>0){
            result.setSuccess(true);
            result.setMsg("删除成功！");
        }
        return result;
    }

    @RequestMapping("toDetail")
    public AppVersion toDetail(Integer id) {
        AppVersion appVersion = appVersionService.selectByPrimaryKey(id);
        return appVersion;
    }

    @RequestMapping("toDetailPage")
    public ModelAndView toDetailPage(){
        return new ModelAndView("/app/detail");
    }
}
