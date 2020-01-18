package com.czy.service;

import com.czy.entity.SysArea;
import com.github.pagehelper.PageInfo;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

public interface SysAreaService extends BaseService<SysArea>{

    PageInfo<SysArea> getPage(Map<String,Object> params);


    OutputStream writeExcel(OutputStream outputStream);

    int readExcel(InputStream inputStream);

    PageInfo<SysArea> getListByCondition(Map<String, Object> params);

    int updateArea(SysArea sysArea);
}
