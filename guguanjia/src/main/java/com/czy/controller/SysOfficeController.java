package com.czy.controller;

import com.czy.entity.*;
import com.czy.service.SysOfficeService;
import com.czy.service.WasteService;
import com.czy.service.WasteTypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @author czy
 * @version 1.0.1
 * @company 东方标准
 * @date 2019/12/30 9:46
 * @description
 */
@RestController
@RequestMapping("manager/office")
public class SysOfficeController {

    @Autowired
    SysOfficeService sysOfficeService;

    @Autowired
    WasteTypeService wasteTypeService;

    @Autowired
    WasteService wasteService;

    @RequestMapping("index")
    public ModelAndView index() {
        return new ModelAndView("/office/office");
    }


    @RequestMapping("list")
    public List<SysOffice> getList() {
        return sysOfficeService.selectAll();
    }


    @RequestMapping("getPage")
    public PageInfo<SysOffice> getPage(@RequestBody Map<String, Object> params) {
        return sysOfficeService.getPage(params);
    }


    @RequestMapping("toUpdatePage")
    public ModelAndView toUpdatePage() {
        return new ModelAndView("/office/update");
    }


    @RequestMapping("toUpdate")
    public SysOffice toUpdate(long id) {
        SysOffice office = sysOfficeService.selectByOid(id);
        System.out.println(office.getWastes().size());
        return office;
    }

    @RequestMapping("selectWasteType")
    public List<WasteType> selectWasteType() {

        return wasteTypeService.selectAll();
    }

    @RequestMapping("selectWaste")
    public List<Waste> selectWaste(long selected) {
        Waste waste = new Waste();
        waste.setParentId(selected);
        return wasteService.select(waste);
    }


    @RequestMapping("delete")
    public Result delete(long id) {
        Result result = new Result();
        SysOffice office = sysOfficeService.selectByPrimaryKey(id);
        office.setDelFlag("1");
        int i = sysOfficeService.updateByPrimaryKeySelective(office);
        if (i > 0) {
            result.setSuccess(true);
            result.setMsg("删除成功");
        }
        return result;
    }

    @RequestMapping("selectByRid")
    public List<SysOffice> selectByRid(long rid) {
        return sysOfficeService.selectByRid(rid);
    }
}
