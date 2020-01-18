package com.czy.mapper;

import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author czy
 * @version 1.0.1
 * @company 东方标准
 * @date 2019/12/25 18:09
 * @description
 */
public class QualificationProvider {

    public String selectByCondition(Map<String, Object> params) {

        StringBuilder str = new StringBuilder();
        str.append("select q.*,su.name uploadUserName,cu.name checkUserName from qualification q " +
                "left JOIN sys_user su on q.upload_user_id=su.id  " +
                "left join sys_user cu on q.check_user_id=cu.id where q.del_flag=0 ");

        if(params.containsKey("type")&& !StringUtils.isEmpty(params.get("type"))){
            str.append(" and q.type=#{type} ");
        }

        if(params.containsKey("check")&& !StringUtils.isEmpty(params.get("check")))
            str.append(" and q.check=#{check} ");

        if(params.containsKey("startDate")&& !StringUtils.isEmpty(params.get("startDate")))
            str.append(" and q.create_date>#{startDate} ");

        if(params.containsKey("endDate")&& !StringUtils.isEmpty(params.get("endDate")))
            str.append(" and q.create_date<#{endDate} ");

        return str.toString();
    }
}
