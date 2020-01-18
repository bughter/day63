package com.czy.mapper;

import com.czy.entity.AppVersion;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class AppVersionSqlProvider {

    public String insertSelective(AppVersion record) {
        BEGIN();
        INSERT_INTO("app_version");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=BIGINT}");
        }
        
        if (record.getPlatform() != null) {
            VALUES("platform", "#{platform,jdbcType=INTEGER}");
        }
        
        if (record.getVersionNo() != null) {
            VALUES("version_no", "#{versionNo,jdbcType=VARCHAR}");
        }
        
        if (record.getForceUpdate() != null) {
            VALUES("force_update", "#{forceUpdate,jdbcType=INTEGER}");
        }
        
        if (record.getDownPath() != null) {
            VALUES("down_path", "#{downPath,jdbcType=VARCHAR}");
        }
        
        if (record.getSize() != null) {
            VALUES("size", "#{size,jdbcType=REAL}");
        }
        
        if (record.getAppExplain() != null) {
            VALUES("app_explain", "#{appExplain,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateDate() != null) {
            VALUES("create_date", "#{createDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateDate() != null) {
            VALUES("update_date", "#{updateDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDelFlag() != null) {
            VALUES("del_flag", "#{delFlag,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateBy() != null) {
            VALUES("create_by", "#{createBy,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(AppVersion record) {
        BEGIN();
        UPDATE("app_version");
        
        if (record.getPlatform() != null) {
            SET("platform = #{platform,jdbcType=INTEGER}");
        }
        
        if (record.getVersionNo() != null) {
            SET("version_no = #{versionNo,jdbcType=VARCHAR}");
        }
        
        if (record.getForceUpdate() != null) {
            SET("force_update = #{forceUpdate,jdbcType=INTEGER}");
        }
        
        if (record.getDownPath() != null) {
            SET("down_path = #{downPath,jdbcType=VARCHAR}");
        }
        
        if (record.getSize() != null) {
            SET("size = #{size,jdbcType=REAL}");
        }
        
        if (record.getAppExplain() != null) {
            SET("app_explain = #{appExplain,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateDate() != null) {
            SET("create_date = #{createDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateDate() != null) {
            SET("update_date = #{updateDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDelFlag() != null) {
            SET("del_flag = #{delFlag,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateBy() != null) {
            SET("create_by = #{createBy,jdbcType=VARCHAR}");
        }
        
        WHERE("id = #{id,jdbcType=BIGINT}");
        
        return SQL();
    }
}