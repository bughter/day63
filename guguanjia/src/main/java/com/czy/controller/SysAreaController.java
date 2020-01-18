package com.czy.controller;

import com.czy.entity.Result;
import com.czy.entity.SysArea;
import com.czy.service.SysAreaService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @author czy
 * @version 1.0.1
 * @company 东方标准
 * @date 2020/1/3 16:09
 * @description
 */

@RestController
@RequestMapping("manager/area")
public class SysAreaController {


    @Autowired
    SysAreaService sysAreaService;

    @RequestMapping("index")
    public ModelAndView index() {
        return new ModelAndView("/area/area");
    }


    @RequestMapping("getPage")
    public PageInfo<SysArea> getPage(@RequestBody Map<String, Object> params) {
        return sysAreaService.getListByCondition(params);
    }

    @RequestMapping("download")
    public void download(HttpServletResponse response) throws IOException {

        response.setHeader("Content-Disposition", "attachment;filename=sysArea.xls");
        OutputStream outputStream = response.getOutputStream();
        outputStream = sysAreaService.writeExcel(outputStream);//响应流数据已经有文件信息
        MultipartFile file;
    }

    @RequestMapping("upload")
    public Result upload(MultipartFile upFile) throws IOException {
        Result result = new Result();
        int i = sysAreaService.readExcel(upFile.getInputStream());

        if (i > 0) {
            result.setMsg("导入成功！");
            result.setSuccess(true);
        }

        return result;
    }

    @RequestMapping("selectAll")
    public List<SysArea> selectAll() {
        return sysAreaService.selectAll();
    }

    @RequestMapping("toUpdatePage")
    public ModelAndView toUpdatePage() {
        return new ModelAndView("/area/save");
    }

    @RequestMapping("toUpdate")
    public SysArea toUpdate(long id){
        return sysAreaService.selectByPrimaryKey(id);
    }

    @RequestMapping("awesome")
    public ModelAndView awesome(){
        return new ModelAndView("/modules/font-awesome");
    }

    @RequestMapping("selectParent")
    public ModelAndView selectParent(){
        return new ModelAndView("/area/select");
    }

    @RequestMapping("doUpdate")
    public Result doUpdate(@RequestBody SysArea area){
        Result result = new Result();

        int i = sysAreaService.updateArea(area);
        if(i>0){
            result.setSuccess(true);
            result.setMsg("更新成功");
        }
        return result;
    }

    @RequestMapping("delete")
    public Result delete(long id){
        Result result = new Result();
        SysArea area=sysAreaService.selectByPrimaryKey(id);
        area.setDelFlag("1");
        int i = sysAreaService.updateByPrimaryKeySelective(area);
        if(i>0){
            result.setSuccess(true);
            result.setMsg("删除成功");
        }
        return result;
    }
}
