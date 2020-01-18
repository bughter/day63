package com.czy.mapper;

import com.czy.entity.SysRole;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author czy
 * @version 1.0.1
 * @company 东方标准
 * @date 2020/1/8 15:52
 * @description
 */
public interface SysRoleMapper extends Mapper<SysRole>{



    @SelectProvider(type = SysRoleProvider.class,method = "selectByCondition")
    List<SysRole> selectByCondition(Map<String,Object> params);


    @InsertProvider(type = SysRoleProvider.class,method = "insertBatch")
    int insertBatch(@Param("cids") List<Long> cids, @Param("rid")long rid);

    @InsertProvider(type = SysRoleProvider.class,method = "insertBatch2")
    int insertBatch2(@Param("rids") List<Long> rids, @Param("uid")long uid);

    @DeleteProvider(type=SysRoleProvider.class,method = "deleteBatch")
    int  deleteBatch(@Param("rid")long rid, @Param("uids")long[] uids);


    @InsertProvider(type = SysRoleProvider.class,method = "insertRoleResource")
    int insertRoleResource(@Param("resIds") long[] resIds,@Param("rid")long rid);

    @InsertProvider(type = SysRoleProvider.class,method = "insertRoleOffice")
    int insertRoleOffice(@Param("oids") long[] oids, @Param("rid")long rid);

    @Delete("delete from sys_role_resource where role_id=#{rid}")
    int deleteRoleResource(long rid);

    @Delete("delete from sys_role_office where role_id=#{rid}")
    int deleteRoleOffice(long rid);
}
