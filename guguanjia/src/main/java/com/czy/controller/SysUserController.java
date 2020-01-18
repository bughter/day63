package com.czy.controller;

import com.czy.entity.Result;
import com.czy.entity.SysOffice;
import com.czy.entity.SysUser;
import com.czy.service.SysUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Map;

//替代Controller   自动添加@ResponseBody转换
@RestController
@RequestMapping("manager/user")
public class SysUserController {

    @Autowired
    SysUserService service;

    @RequestMapping("index")
    public ModelAndView toIndex(){
        return new ModelAndView("/user/user-list");
    }

    @RequestMapping("getPage")
//    @ResponseBody
    public PageInfo<SysUser> getPage(@RequestBody Map<String,Object> params){
        return service.selectByCondition(params);
    }

    @RequestMapping("delete")
    public Result delete(long id){
        Result result = new Result();
        SysUser user=service.selectByPrimaryKey(id);
        user.setDelFlag("1");
        int i = service.updateByPrimaryKeySelective(user);
        if(i>0){
            result.setSuccess(true);
            result.setMsg("删除成功");
        }
        return result;
    }


    @RequestMapping("insert")
    public Result insert(@RequestBody SysUser user){
        Result result = new Result();
        user.setUpdateDate(new Date());
        user.setCreateBy("admin");
        user.setCompanyId(user.getOfficeId());
        user.setCreateDate(new Date());
        user.setDelFlag("0");
        int i =service.insertUser(user,user.getRoleIds());
        SysUser sysUser=new SysUser();
        sysUser.setUsername(user.getUsername());
        sysUser=service.select(sysUser).get(0);
        i+=service.insertBatch(sysUser.getId(),user.getRoleIds());
        if(i>0){
            result.setSuccess(true);
            result.setMsg("新增成功");
        }
        return result;
    }

    @RequestMapping("toUpdatePage")
    public ModelAndView toUpdatPage(){
        return new ModelAndView("/user/user-save");
    }

    @RequestMapping("toUpdate")
    public SysUser toUpdate(long id){
        return service.selectByPrimaryKey(id);
    }
//
//
////
//
//    @RequestMapping("detail")
//    public SysUser detail(long id){
//        return service.selectOneByCondition(id);
//    }
//

//    /**
//     * 根据传入的角色id查询已经分配该角色的用户信息
//     * @param rid
//     * @return
//     */
//    @RequestMapping("selectByRid")
//    public List<SysUser> selectByRid(long rid){
//        return service.selectByRid(rid);
//    }


    @RequestMapping("selectNoRole")
    public List<SysUser> selectNoRole(long rid,long oid){
        return service.selectNoRole(rid,oid);
    }
    @RequestMapping("selectByRid")
    public List<SysUser> selectByRid(long rid){
        return service.selectByRid(rid);
    }

}
