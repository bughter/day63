package com.czy.service;

import com.czy.entity.Examine;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface ExamineService extends BaseService<Examine>{

    PageInfo<Examine> getPageByCondition(Map<String,Object> params);
}
