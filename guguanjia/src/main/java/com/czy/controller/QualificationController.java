package com.czy.controller;

import com.czy.entity.AppVersion;
import com.czy.entity.Qualification;
import com.czy.entity.Result;
import com.czy.service.QualificationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Map;

/**
 * @author czy
 * @version 1.0.1
 * @company 东方标准
 * @date 2019/12/25 16:14
 * @description
 */
@RequestMapping("manager/qualification")
@RestController
public class QualificationController {

    @Autowired
    QualificationService qualificationService;

    @RequestMapping("index")
    public ModelAndView index(){
        return new ModelAndView("/qualification/index");
    }

    @RequestMapping("getPage")
    public PageInfo<Qualification> getPage(@RequestBody Map<String,Object> params){

        return qualificationService.getPage(params);
    }

    @RequestMapping("toUpdate")
    public Qualification toUpdate(Integer id) {
        Qualification qualification = qualificationService.selectByPrimaryKey(id);
        return qualification;
    }

    @RequestMapping("toUpdatePage")
    public ModelAndView toUpdatePage(){
        return new ModelAndView("/qualification/update");
    }

    @RequestMapping("doUpdate")
    public Result doUpdate(@RequestBody Qualification qualification){
        qualification.setUpdateDate(new Date());
        qualification.setCheck(1);
        Result result=new Result();
        int i=qualificationService.updateByPrimaryKeySelective(qualification);
        if(i>0){
            result.setSuccess(true);
            result.setMsg("更新成功！");
        }
        return result;
    }
    @RequestMapping("doUpdate2")
    public Result doUpdate2(@RequestBody Qualification qualification){
        qualification.setUpdateDate(new Date());
        qualification.setCheck(2);
        Result result=new Result();
        int i=qualificationService.updateByPrimaryKeySelective(qualification);
        if(i>0){
            result.setSuccess(true);
            result.setMsg("更新成功！");
        }
        return result;
    }
}
