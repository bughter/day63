package com.czy.service;

import com.czy.entity.SysRole;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface SysRoleService extends BaseService<SysRole>{

    PageInfo<SysRole> getPageByCondition(Map<String,Object> params);

    int insertBatch(List<Long> cids, long rid);

    int deleteBatch(long rid, long[] uids);

    int update(Map<String, Object> params);
}
