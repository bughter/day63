package com.czy.service;

import com.czy.entity.SysOffice;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface SysOfficeService extends BaseService<SysOffice>{

    PageInfo<SysOffice> getPage(Map<String,Object> params);

    SysOffice selectByOid(long oid);


    List<SysOffice> selectByRid(long rid);
}
