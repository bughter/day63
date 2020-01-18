package com.czy.service;

import com.czy.entity.SysLog;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface SysLogService extends  BaseService<SysLog>{
    PageInfo<SysLog> selectByCondition(Map<String, Object> params);
}
