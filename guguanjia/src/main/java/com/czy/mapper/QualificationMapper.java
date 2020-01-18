package com.czy.mapper;

import com.czy.entity.Qualification;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface QualificationMapper extends Mapper<Qualification> {

    @SelectProvider(type = QualificationProvider.class,method ="selectByCondition" )
    List<Qualification> selectByCondition(Map<String,Object> params);
}
