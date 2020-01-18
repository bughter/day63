package com.czy.mapper;


import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author czy
 * @version 1.0.1
 * @company 东方标准
 * @date 2019/12/26 16:29
 * @description
 */
public class ExamineSqlProvider {

    public String selectByCondition(Map<String ,Object> params){
        StringBuilder str=new StringBuilder();
        str.append("select ex.* , su.name userName,so.name officeName from examine ex ,sys_user su,sys_office so " +
                "where ex.examine_user_id=su.id and su.office_id=so.id and ex.del_flag=0 ");

        if(params.containsKey("type") && !StringUtils.isEmpty(params.get("type"))){
            str.append(" and ex.type=#{type} ");
        }
        if(params.containsKey("officeId")&&!StringUtils.isEmpty(params.get("officeId"))){
            str.append(" and so.id=#{officeId} ");
        }
        if(params.containsKey("userName") && !StringUtils.isEmpty(params.get("userName"))){
            str.append(" and su.name like concat('%',#{userName},'%') ");
        }

        return str.toString();
    }
}
