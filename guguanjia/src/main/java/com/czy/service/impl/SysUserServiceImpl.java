package com.czy.service.impl;

import com.czy.entity.SysUser;
import com.czy.mapper.SysRoleMapper;
import com.czy.mapper.SysUserMapper;
import com.czy.service.SysUserService;
import com.czy.utils.EncryptUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author czy
 * @version 1.0.1
 * @company 东方标准
 * @date 2020/1/7 15:49
 * @description
 */

@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Override
    public SysUser checkSysUser(SysUser record) {
        record.setPassword(EncryptUtils.MD5_HEX(EncryptUtils.MD5_HEX(record.getPassword()) + record.getUsername()));
        List<SysUser> select = mapper.select(record);
        if (select.size() > 0) {
            return select.get(0);
        }
        return null;
    }

    @Override
    public List<SysUser> selectNoRole(long rid, long oid) {
        return sysUserMapper.selectNoRole(rid, oid);
    }

    @Override
    public List<SysUser> selectByRid(long rid) {
        return sysUserMapper.selectByRid(rid);
    }

    @Override
    public PageInfo<SysUser> selectByCondition(Map<String, Object> params) {
        if (!params.containsKey("pageNum") || StringUtils.isEmpty(params.get("pageNum"))) {
            params.put("pageNmu", 1);
        }
        if (!params.containsKey("pageSize") || StringUtils.isEmpty(params.get("pageSize"))) {
            params.put("pageSize", 5);
        }
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage((int) params.get("pageNum"), (int) params.get("pageSize"));
        List<SysUser> list = sysUserMapper.selectByCondition(params);
        return new PageInfo<SysUser>(list);
    }

    @Override
    public int insertUser(SysUser user, String roleIds) {
        int i = 0;
        i = mapper.insertSelective(user);
//        SysUser user2=sysUserMapper.select(user).get(0);
//        List<Long> ridList = new ArrayList<>();
//        if (!StringUtils.isEmpty(roleIds)) {
//            String[] rids = roleIds.split(",");
//            ridList = new ArrayList<>();
//            for (String s : rids) {
//                ridList.add(Long.valueOf(s));
//            }
//            i += sysRoleMapper.insertBatch2(ridList,user2.getId());
//        }
        return i;
    }
    public int insertBatch(Long uid,String roleIds){
        int i=0;
        List<Long> ridList = new ArrayList<>();
        if (!StringUtils.isEmpty(roleIds)) {
            String[] rids = roleIds.split(",");
            ridList = new ArrayList<>();
            for (String s : rids) {
                ridList.add(Long.valueOf(s));
            }
            i += sysRoleMapper.insertBatch2(ridList,uid);
        }
        return i;
    }


}
