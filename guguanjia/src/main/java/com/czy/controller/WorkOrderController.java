package com.czy.controller;

import com.czy.entity.WorkOrder;
import com.czy.service.WorkOrderService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author czy
 * @version 1.0.1
 * @company 东方标准
 * @date 2020/1/5 15:15
 * @description
 */

@RequestMapping("manager/admin/work")
@RestController
public class WorkOrderController {

    @Autowired
    WorkOrderService workOrderService;

    @RequestMapping("index")
    public ModelAndView index(){
        return new ModelAndView("/work/admin/work");
    }


    @RequestMapping("getPage")
    public PageInfo<WorkOrder> getPage(@RequestBody Map<String,Object> params){

        return workOrderService.selectAll(params);
    }

    @RequestMapping("toDetail")
    public ModelAndView toDetail(){
        return new ModelAndView("/work/work-detail");
    }

    @RequestMapping("selectByOid")
    public Map<String,Object> selectByOid(long oid){
        return workOrderService.selectByOid(oid);
    }
    @RequestMapping("toPrint")
    public ModelAndView toPrint(){
        return new ModelAndView("/work/print");
    }

}
