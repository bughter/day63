package com.czy.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author czy
 * @version 1.0.1
 * @company 东方标准
 * @date 2020/1/15 18:48
 * @description
 */
public class SysUserProvider {

    public String selectByCondition(Map<String,Object> params){
        StringBuilder str=new StringBuilder();
        str.append("select su.* ,sr.name roleName,so.name officeName from sys_user su " +
                "left join sys_office so on so.id=su.office_id " +
                "left join sys_user_role sur on sur.user_id=su.id " +
                "left join sys_role sr on sur.role_id = sr.id " +
                "where su.del_flag=0 ");

        if(params.containsKey("officeId")&& !StringUtils.isEmpty(params.get("officeId"))){
            str.append(" and so.id=#{officeId} ");
        }
        if(params.containsKey("name")&& !StringUtils.isEmpty(params.get("name"))){
            str.append(" and su.name like concat('%',#{name},'%') ");
        }
        if(params.containsKey("roleId")&& !StringUtils.isEmpty(params.get("roleId"))){
            str.append(" and sr.id=#{roleId} ");
        }
        if(params.containsKey("no")&& !StringUtils.isEmpty(params.get("no"))){
            str.append(" and su.no=#{no} ");
        }
        return str.toString();
    }

}
