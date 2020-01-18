package com.czy.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author czy
 * @version 1.0.1
 * @company 东方标准
 * @date 2020/1/8 15:52
 * @description
 */
public class SysRoleProvider {


    public String selectByCondition(Map<String, Object> params) {
        StringBuilder str = new StringBuilder();
        str.append("select sr.*,so.name officeName from sys_role sr left join sys_office so on sr.office_id=so.id where 1=1 ");

        if (params.containsKey("name") && !StringUtils.isEmpty(params.get("name"))) {
            str.append(" and sr.name like concat('%',#{name},'%')");
        }
        if (params.containsKey("dataScope") && !StringUtils.isEmpty(params.get("dataScope"))) {
            str.append(" and sr.data_scope=#{dataScope}");
        }
        if (params.containsKey("officeId") && !StringUtils.isEmpty(params.get("officeId"))) {
            str.append(" and so.id=#{officeId}");
        }
        return str.toString();
    }


    public String insertBatch(@Param("cids") List<Long> cids, @Param("rid")long rid){
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO `sys_user_role`( `role_id`, `user_id`, `create_by`, `create_date`, " +
                "`update_by`, `update_date`, `del_flag`) VALUES ");

        for (int i = 0; i < cids.size(); i++) {
            sb.append("(#{rid},#{cids["+i+"]},null,now(),null,now(),0),");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    public String insertBatch2(@Param("rids") List<Long> rids, @Param("uid")long uid){
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO `sys_user_role`( `role_id`, `user_id`, `create_by`, `create_date`, " +
                "`update_by`, `update_date`, `del_flag`) VALUES ");

        for (int i = 0; i < rids.size(); i++) {
            sb.append("(#{rids["+i+"]},#{uid},null,now(),null,now(),0),");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    public String  deleteBatch(@Param("rid")long rid, @Param("uids")long[] uids){
        StringBuilder sb = new StringBuilder();
        sb.append("delete " +
                "from " +
                " sys_user_role " +
                "where " +
                " role_id=#{rid} " +
                "and " +
                " user_id " +
                "in ");
        sb.append("(");
        for (int i = 0; i < uids.length; i++) {
            sb.append("#{uids["+i+"]},");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(")");
        return sb.toString();
    }


    public String insertRoleResource(@Param("resIds") long[] resIds,@Param("rid")long rid){
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO `sys_role_resource`(  `role_id`, `resource_id`, " +
                "`create_by`, `create_date`, `update_by`, `update_date`, `del_flag`) VALUES ");

        for (int i = 0; i < resIds.length; i++) {
            sb.append("(#{rid},#{resIds["+i+"]},null,now(),null,now(),0),");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    public String insertRoleOffice(@Param("oids") long[] oids,@Param("rid")long rid){
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO `sys_role_office`(`role_id`, `office_id`, `id`, `create_by`, `create_date`, `update_by`, `update_date`, `del_flag`) VALUES ");

        for (int i = 0; i < oids.length; i++) {
            sb.append("(#{rid},#{oids["+i+"]},null,null,now(),null,now(),0),");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}
