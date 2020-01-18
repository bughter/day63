package com.czy.mapper;

import com.czy.entity.SysArea;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SysAreaMapper extends Mapper<SysArea> {


    @InsertProvider(type=SysAreaProvider.class,method="insertBath")
    int insertBath(@Param("areas") List<SysArea> areas);


//    @SelectProvider(type=SysAreaProvider.class,method = "getListByCondition")
@Select("select sub.*,parent.name parentName " +
        "from " +
        " sys_area sub,sys_area parent " +
        "where " +
        " sub.del_flag='0'" +
        " and sub.parent_ids like CONCAT('%',#{aid},'%') " +
        "and " +
        " sub.parent_id=parent.id")
    List<SysArea> getListByCondition(@Param("aid")Integer aid);

    @Select("select sub.*,parent.name parentName " +
            "from " +
            " sys_area sub,sys_area parent " +
            "where " +
            " sub.del_flag='0' " +
            " and sub.name like CONCAT('%',#{areaName},'%') " +
            "and " +
            " sub.parent_id=parent.id")
    List<SysArea> getListByCondition2(@Param("areaName")String areaName);


    @Update("update " +
            " sys_area " +
            "set " +
            " parent_ids=REPLACE(parent_ids,#{oldParentIds},#{parentIds}) " +
            "where " +
            " parent_ids like concat('%,',#{id},',%') ")
    int updateParentIds(SysArea sysArea);
}
