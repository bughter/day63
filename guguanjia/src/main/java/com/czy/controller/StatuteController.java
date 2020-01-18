package com.czy.controller;

import com.czy.entity.Result;
import com.czy.entity.Statute;
import com.czy.service.StatuteService;
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
 * @date 2019/12/30 16:13
 * @description
 */

@RequestMapping("manager/statute")
@RestController
public class StatuteController {

    @Autowired
    StatuteService statuteService;

    @RequestMapping("index")
    public ModelAndView index() {
        return new ModelAndView("/statute/index");
    }

    @RequestMapping("getPage")
    public PageInfo<Statute> getPage(@RequestBody Map<String, Object> params) {
        return statuteService.getPage(params);
    }

    @RequestMapping("insert")
    public Result insert(@RequestBody Statute statute) {
        statute.setDelFlag("0");
        statute.setCreateBy("admin");
        statute.setPubDate(new Date());
        statute.setCreateDate(new Date());
        statute.setUpdateDate(new Date());
        int i = statuteService.insertSelective(statute);
        Result result = new Result();
        if (i > 0) {
            result.setSuccess(true);
            result.setMsg("添加成功！");
        }
        return result;
    }

    @RequestMapping("toUpdate")
    public Statute toUpdate(long id) {
        return statuteService.selectByPrimaryKey(id);
    }

    @RequestMapping("toUpdatePage")
    public ModelAndView toUpdatePage() {
        return new ModelAndView("/statute/update");
    }


    @RequestMapping("doUpdate")
    public Result doUpdate(@RequestBody Statute statute) {
        Result result = new Result();
        int i = statuteService.updateByPrimaryKeySelective(statute);

        if(i>0){
            result.setMsg("更新成功！");
            result.setSuccess(true);
        }

        return result;
    }

    @RequestMapping("delete")
    public Result doUpdate(long id) {
        Result result = new Result();
        Statute statute=statuteService.selectByPrimaryKey(id);
        statute.setDelFlag("1");
        int i = statuteService.updateByPrimaryKeySelective(statute);
        if(i>0){
            result.setMsg("删除成功！");
            result.setSuccess(true);
        }

        return result;
    }
}
