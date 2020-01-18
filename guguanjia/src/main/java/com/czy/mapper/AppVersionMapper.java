package com.czy.mapper;

import com.czy.entity.AppVersion;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AppVersionMapper extends Mapper<AppVersion> {
//    @Delete({
//        "delete from app_version",
//        "where id = #{id,jdbcType=BIGINT}"
//    })
//    int deleteByPrimaryKey(Long id);
//
//    @Insert({
//        "insert into app_version (id, platform, ",
//        "version_no, force_update, ",
//        "down_path, size, app_explain, ",
//        "create_date, update_date, ",
//        "del_flag, create_by)",
//        "values (#{id,jdbcType=BIGINT}, #{platform,jdbcType=INTEGER}, ",
//        "#{versionNo,jdbcType=VARCHAR}, #{forceUpdate,jdbcType=INTEGER}, ",
//        "#{downPath,jdbcType=VARCHAR}, #{size,jdbcType=REAL}, #{appExplain,jdbcType=VARCHAR}, ",
//        "#{createDate,jdbcType=TIMESTAMP}, #{updateDate,jdbcType=TIMESTAMP}, ",
//        "#{delFlag,jdbcType=VARCHAR}, #{createBy,jdbcType=VARCHAR})"
//    })
//    int insert(AppVersion record);
//
//    @InsertProvider(type= AppVersionSqlProvider.class, method="insertSelective")
//    int insertSelective(AppVersion record);
//
//    @Select({
//        "select",
//        "id, platform, version_no, force_update, down_path, size, app_explain, create_date, ",
//        "update_date, del_flag, create_by",
//        "from app_version",
//        "where id = #{id,jdbcType=BIGINT}"
//    })
//    @Results({
//        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
//        @Result(column="platform", property="platform", jdbcType=JdbcType.INTEGER),
//        @Result(column="version_no", property="versionNo", jdbcType=JdbcType.VARCHAR),
//        @Result(column="force_update", property="forceUpdate", jdbcType=JdbcType.INTEGER),
//        @Result(column="down_path", property="downPath", jdbcType=JdbcType.VARCHAR),
//        @Result(column="size", property="size", jdbcType=JdbcType.REAL),
//        @Result(column="app_explain", property="appExplain", jdbcType=JdbcType.VARCHAR),
//        @Result(column="create_date", property="createDate", jdbcType=JdbcType.TIMESTAMP),
//        @Result(column="update_date", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
//        @Result(column="del_flag", property="delFlag", jdbcType=JdbcType.VARCHAR),
//        @Result(column="create_by", property="createBy", jdbcType=JdbcType.VARCHAR)
//    })
//    AppVersion selectByPrimaryKey(Long id);
//
//    @UpdateProvider(type= AppVersionSqlProvider.class, method="updateByPrimaryKeySelective")
//    int updateByPrimaryKeySelective(AppVersion record);
//
//    @Update({
//        "update app_version",
//        "set platform = #{platform,jdbcType=INTEGER},",
//          "version_no = #{versionNo,jdbcType=VARCHAR},",
//          "force_update = #{forceUpdate,jdbcType=INTEGER},",
//          "down_path = #{downPath,jdbcType=VARCHAR},",
//          "size = #{size,jdbcType=REAL},",
//          "app_explain = #{appExplain,jdbcType=VARCHAR},",
//          "create_date = #{createDate,jdbcType=TIMESTAMP},",
//          "update_date = #{updateDate,jdbcType=TIMESTAMP},",
//          "del_flag = #{delFlag,jdbcType=VARCHAR},",
//          "create_by = #{createBy,jdbcType=VARCHAR}",
//        "where id = #{id,jdbcType=BIGINT}"
//    })
//    int updateByPrimaryKey(AppVersion record);

}