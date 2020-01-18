package com.czy.service.impl;

import com.czy.entity.SysResource;
import com.czy.mapper.SysResourceMapper;
import com.czy.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author czy
 * @version 1.0.1
 * @company 东方标准
 * @date 2020/1/10 15:11
 * @description
 */
@Service
public class SysResourceServiceImpl extends BaseServiceImpl<SysResource> implements SysResourceService {

    @Autowired
    SysResourceMapper sysResourceMapper;

    @Override
    public List<SysResource> selectByRid(long rid){
        return sysResourceMapper.selectByRid(rid);
    }


    @Override
    @Cacheable(cacheNames = "resourceCache",key = "'cn.nyse.service.impl.SysResourceServiceImpl:selectAll'")
    public List<SysResource> selectAll() {
        return super.selectAll();
    }

    @Override
    public List<SysResource> selectByUid(long uid) {
        List<SysResource> sysResources = sysResourceMapper.selectByUid(uid);
//        HashSet<SysResource> resourceSet = new HashSet<>();
//        resourceSet.addAll(sysResources);//必须重写equals才能保证去重
        return sysResources;
    }
}
