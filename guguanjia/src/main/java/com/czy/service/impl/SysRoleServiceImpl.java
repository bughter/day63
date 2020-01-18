package com.czy.service.impl;

import com.czy.entity.SysRole;
import com.czy.mapper.SysRoleMapper;
import com.czy.service.SysRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author czy
 * @version 1.0.1
 * @company 东方标准
 * @date 2020/1/8 15:43
 * @description
 */

@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleService {
    @Autowired
    SysRoleMapper sysRoleMapper;

    @Override
    public PageInfo<SysRole> getPageByCondition(Map<String,Object> params) {
        if (!params.containsKey("pageNum") || StringUtils.isEmpty(params.get("pageNum"))) {
            params.put("pageNum", 1);
        }

        if (!params.containsKey("pageSize") || StringUtils.isEmpty(params.get("pageSize"))) {
            params.put("pageSize", 5);
        }
        PageHelper.startPage((int) params.get("pageNum"), (int) params.get("pageSize"));
        List<SysRole> list=sysRoleMapper.selectByCondition(params);
        return new PageInfo<SysRole>(list);
    }

    @Override
    public int insertBatch(List<Long> cids,long rid){
        return sysRoleMapper.insertBatch(cids,rid);
    }

    @Override
    public int deleteBatch(long rid, long[] uids){
        return  sysRoleMapper.deleteBatch(rid,uids);
    }

    @Override
    public int update(Map<String, Object> params){
        long rid = (long) params.get("rid");
        long[] resIds = (long[]) params.get("resIds");
        long[] oids = (long[]) params.get("oids");
        SysRole sysRole = (SysRole) params.get("role");
        int result=0;
        result+=sysRoleMapper.updateByPrimaryKey(sysRole);
        result+=sysRoleMapper.deleteRoleResource(rid);
        result+=sysRoleMapper.insertRoleResource(resIds,rid);
        result+=sysRoleMapper.deleteRoleOffice(rid);
        result+=sysRoleMapper.insertRoleOffice(oids,rid);
        return result;
    }
}
