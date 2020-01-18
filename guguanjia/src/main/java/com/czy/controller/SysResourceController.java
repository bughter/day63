package com.czy.controller;

import com.czy.entity.SysResource;
import com.czy.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

//替代Controller   自动添加@ResponseBody转换
@RestController
@RequestMapping("manager/menu")
public class SysResourceController {

    @Autowired
    SysResourceService service;

    

    @RequestMapping("list")
    public List<SysResource> list (){
        return service.selectAll();
    }

    @RequestMapping("selectByRid")
    public List<SysResource> selectByRid(long rid){
        return service.selectByRid(rid);
    }
    @RequestMapping("selectByUid")
    public List<SysResource> selectByUid(long uid, HttpSession session){
        //获取用户的所有权限，并且放入状态管理
        List<SysResource> sysResources = service.selectByUid(uid);
        session.setAttribute("resources",sysResources);
        return sysResources;
    }

}
