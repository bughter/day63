package com.czy.controller;

import com.czy.entity.Result;
import com.czy.entity.SysRole;
import com.czy.service.SysRoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author czy
 * @version 1.0.1
 * @company 东方标准
 * @date 2020/1/8 15:31
 * @description
 */
@RestController
@RequestMapping("manager/role")
public class SysRoleController {

    @Autowired
    SysRoleService sysRoleService;


    @RequestMapping("index")
    public ModelAndView index() {
        return new ModelAndView("/role/role");
    }

    @RequestMapping("getPage")
    public PageInfo<SysRole> getPage(@RequestBody Map<String, Object> params) {

        return sysRoleService.getPageByCondition(params);
    }

    @RequestMapping("toUser")
    public SysRole toUser(long id) {
        return sysRoleService.selectByPrimaryKey(id);
    }

    @RequestMapping("toUserPage")
    public ModelAndView toUserPage() {
        return new ModelAndView("/role/role-user");
    }


    @RequestMapping("insertUser")
    public Result insertUser(long rid,String str){
        String[] ids=str.split("@");
        List<Long> cids=new ArrayList<>();
        for(String s:ids){
            cids.add(Long.valueOf(s));
        }
        int i = sysRoleService.insertBatch(cids, rid);
        Result result = new Result();
        if(i>0){
            result.setMsg("更新成功");
            result.setSuccess(true);
        }

        return result;
    }

    @RequestMapping("deleteUser")
    public Result deleteUser(long rid,String str){
        String[] ids=str.split("@");
        long[] uids=new long[ids.length];
        for(int i=0;i<ids.length;i++){
            uids[i]=Long.valueOf(ids[i]);
        }
        int i = sysRoleService.deleteBatch(rid,uids);
        Result result = new Result();
        if(i>0){
            result.setMsg("删除成功");
            result.setSuccess(true);
        }

        return result;

    }

    @RequestMapping("toUpdatePage")
    public ModelAndView toUpdatePage() {
        return new ModelAndView("/role/role-save");
    }

    @RequestMapping("toOffice")
    public ModelAndView toOffice(){
        return new ModelAndView("/role/role-select");
    }


    @RequestMapping("delete")
    public Result delete(long rid){
        SysRole sysRole=sysRoleService.selectByPrimaryKey(rid);
        sysRole.setDelFlag("1");
        int i = sysRoleService.updateByPrimaryKeySelective(sysRole);
        Result result = new Result();
        if(i>0){
            result.setMsg("删除成功");
            result.setSuccess(true);
        }

        return result;

    }

    @RequestMapping("doUpdate")
    public Result doUpdate(@RequestBody Map<String,Object> params){
        Result result = new Result();
//        if (service.update(params)==5){
//            result.setSuccess(true);
//            result.setMsg("更新成功");
//        }
        return result;
    }
}
