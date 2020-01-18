package com.czy.mapper;

import com.czy.entity.Waste;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface WasteMapper extends Mapper<Waste> {

//    @Select("select " +
//            " wa.*,wt.code wasteTypeCode " +
//            "from " +
//            " sys_office so  " +
//            "LEFT JOIN " +
//            " office_waste ow " +
//            "on " +
//            " so.id=ow.office_id " +
//            "LEFT JOIN " +
//            " waste wa " +
//            "on " +
//            " ow.waste_id=wa.id " +
//            "LEFT JOIN " +
//            " waste_type wt " +
//            "on " +
//            " wa.parent_id=wt.id " +
//            "where " +
//            " so.id=#{oid}")
    @Select("select  wa.*,wt.code wasteTypeCode from sys_office so ,office_waste ow ,waste wa , waste_type wt  " +
            "where so.id=ow.office_id and ow.waste_id=wa.id and wa.parent_id=wt.id and so.id=#{oid}")
    List<Waste> selectByOid(long oid);
}
