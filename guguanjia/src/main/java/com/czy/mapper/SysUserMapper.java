package com.czy.mapper;

import com.czy.entity.SysUser;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author czy
 * @version 1.0.1
 * @company 东方标准
 * @date 2020/1/7 16:13
 * @description
 */
public interface SysUserMapper extends Mapper<SysUser> {

    @Select("select " +
            " * " +
            "from " +
            " sys_user " +
            "where " +
            " office_id=#{oid} " +
            "and " +
            " id  " +
            "not in " +
            "( " +
            "select " +
            " sur.user_id " +
            "from " +
            " sys_role sr,sys_user_role sur " +
            "where " +
            " sr.id=#{rid} " +
            "and " +
            " sr.id=sur.role_id " +
            ")")
    List<SysUser> selectNoRole(@Param("rid") long rid, @Param("oid") long oid);


    @Select("select su.* from sys_user su, sys_user_role sur,sys_role sr " +
            "where sur.role_id=sr.id and sur.user_id=su.id and sr.id=#{rid}")
    List<SysUser> selectByRid(@Param("rid") long rid);


    @SelectProvider(type = SysUserProvider.class,method = "selectByCondition")
    List<SysUser> selectByCondition(Map<String,Object> params);



    /**
     * 根据用户id查询 用户信息和公司信息
     */
    @Select("select  " +
            " su.*,so.name officeName,so.id officeId " +
            "from " +
            " sys_user su,sys_office so " +
            "where " +
            " su.id=#{uid} " +
            "and " +
            " su.office_id=so.id")
    @Results(value = {
            @Result(property = "id", column = "su.id"),
            @Result(property = "sysOffice.id", column = "officeId"),
            @Result(property = "sysOffice.name", column = "officeName")
    })
    SysUser selectById(long uid);
}
