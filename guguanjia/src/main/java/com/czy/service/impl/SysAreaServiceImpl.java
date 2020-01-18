package com.czy.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.czy.entity.SysArea;
import com.czy.entity.SysAreaListener;
import com.czy.mapper.SysAreaMapper;
import com.czy.service.SysAreaService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author czy
 * @version 1.0.1
 * @company 东方标准
 * @date 2020/1/3 16:12
 * @description
 */

@Service
public class SysAreaServiceImpl extends BaseServiceImpl<SysArea> implements SysAreaService {


    @Autowired
    SysAreaMapper sysAreaMapper;

    @Override
    public PageInfo<SysArea> getPage(Map<String, Object> params) {

        if (!params.containsKey("pageNum") || StringUtils.isEmpty(params.get("pageNum"))) {
            params.put("pageNum", 1);
        }
        if (!params.containsKey("pageSize") || StringUtils.isEmpty(params.get("pageSize"))) {
            params.put("pageSize", 5);
        }
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage((int) params.get("pageNum"), (int) params.get("pageSize"));

        List<SysArea> list = mapper.selectAll();
        return new PageInfo<SysArea>(list);
    }



    @Override
    public PageInfo<SysArea> getListByCondition(Map<String, Object> params) {
        if (!params.containsKey("pageNum") || StringUtils.isEmpty(params.get("pageNum"))) {
            params.put("pageNum", 1);
        }
        if (!params.containsKey("pageSize") || StringUtils.isEmpty(params.get("pageSize"))) {
            params.put("pageSize", 5);
        }
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage((int) params.get("pageNum"), (int) params.get("pageSize"));
        List<SysArea> list = new ArrayList<>();
        if(params.containsKey("areaName") && !StringUtils.isEmpty(params.get("areaName"))) {
            list= sysAreaMapper.getListByCondition2(params.get("areaName").toString());
        }else{
            list = sysAreaMapper.getListByCondition((int)params.get("aid"));
        }
        return new PageInfo<SysArea>(list);

    }

    @Override
    public OutputStream writeExcel(OutputStream outputStream) {

        //获取excel写出对象
        ExcelWriter writer = EasyExcel.write(outputStream, SysArea.class).build();
        //获取sheet对象
        WriteSheet sheet = EasyExcel.writerSheet("sysarea数据").build();
        List<SysArea> sysAreas = mapper.selectAll();
        writer.write(sysAreas, sheet);//将数据写出到excel表的对应sheet位置

        //关闭资源
        writer.finish();
        return outputStream;
    }

    @Override
    public int readExcel(InputStream inputStream) {
        int result = 0;
        ExcelReader excelReader = EasyExcel.read(inputStream,
                SysArea.class, new SysAreaListener(sysAreaMapper)).build();
        ReadSheet sheet = EasyExcel.readSheet(0).build();
        excelReader.read(sheet);//读资源
        excelReader.finish();
        result += 1;
        return result;
    }

    @Override
    public int updateArea(SysArea sysArea){
        int i = 0;
        i += sysAreaMapper.updateByPrimaryKey(sysArea);

        if(!sysArea.getOldParentIds().equals(sysArea.getParentIds())){
            i+= sysAreaMapper.updateParentIds(sysArea);//更新所有的子区域的parentIds
        }
        return i;
    }
}
