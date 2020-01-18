package com.czy.controller;

import com.czy.entity.Examine;
import com.czy.service.ExamineService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author czy
 * @version 1.0.1
 * @company 东方标准
 * @date 2019/12/26 15:18
 * @description
 */

@RequestMapping("manager/examine")
@RestController
public class ExamineController {

    @Autowired
    ExamineService examineService;


    @RequestMapping("index")
    public ModelAndView index() {
        return new ModelAndView("/examine/index");
    }

    @RequestMapping("getPage")
    public PageInfo<Examine> getPage(@RequestBody Map<String,Object> params){

        return examineService.getPageByCondition(params);

    }
}
