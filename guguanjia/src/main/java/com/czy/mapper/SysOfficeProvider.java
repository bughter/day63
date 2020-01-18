package com.czy.mapper;

import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author czy
 * @version 1.0.1
 * @company 东方标准
 * @date 2020/1/6 19:42
 * @description
 */
public class SysOfficeProvider {


    public String getPageByCondition(Map<String,Object> params){
        StringBuilder str=new StringBuilder();
        str.append("select so.*,sa.name areaName from sys_office so left join sys_area sa on so.area_id=sa.id where so.del_flag='0' ");

        if(params.containsKey("officeName")&& !StringUtils.isEmpty(params.get("officeName"))){
            str.append(" and so.name like CONCAT('%',#{officeName},'%') ");
            return str.toString();
        }

        if(params.containsKey("aid")&& !StringUtils.isEmpty(params.get("aid")) && !"0".equals(params.get("aid").toString())){
            str.append(" and so.id=#{aid}");
        }
        return str.toString();
    }
}
