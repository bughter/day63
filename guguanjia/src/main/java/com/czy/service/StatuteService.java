package com.czy.service;

import com.czy.entity.Statute;
import com.github.pagehelper.PageInfo;

import java.util.Map;


public interface StatuteService extends BaseService<Statute>{
    PageInfo<Statute> getPage(Map<String,Object> params);
}
