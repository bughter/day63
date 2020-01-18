package com.czy.mapper;

import org.springframework.util.StringUtils;

import java.util.Map;

public class SysLogProvider {

    public String  selectByCondition(Map<String, Object> params){


        StringBuilder sb = new StringBuilder();
        sb.append("select  " +
                " *  " +
                "from " +
                " sys_log " +
                "where " +
                " del_flag=0 ");
        if(params.containsKey("type")&&!StringUtils.isEmpty(params.get("type"))){
            sb.append("  type=#{type} ");
        }
        if(params.containsKey("description")&&!StringUtils.isEmpty(params.get("description"))){
            sb.append("  description like  concat('%',#{description},'%')");
        }

        System.out.println(sb.toString());
        return sb.toString();

       
    }
}
