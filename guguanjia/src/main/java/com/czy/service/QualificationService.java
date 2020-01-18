package com.czy.service;

import com.czy.entity.Qualification;
import com.github.pagehelper.PageInfo;

import java.util.Map;


public interface QualificationService extends BaseService<Qualification>{

    PageInfo<Qualification> getPage(Map<String, Object> params);
}
