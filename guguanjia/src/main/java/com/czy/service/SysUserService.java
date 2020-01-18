package com.czy.service;

import com.czy.entity.SysUser;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysUserService extends BaseService<SysUser>{
    SysUser checkSysUser(SysUser record);

    List<SysUser> selectNoRole(long rid, long oid);

    List<SysUser> selectByRid(@Param("rid") long rid);

    PageInfo<SysUser> selectByCondition(Map<String,Object> params);

    int insertUser(SysUser user,String roleIds);
    int insertBatch(Long uid,String roleIds);
}
