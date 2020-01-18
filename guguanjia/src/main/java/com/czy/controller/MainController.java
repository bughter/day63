package com.czy.controller;

import com.czy.entity.Result;
import com.czy.entity.SysUser;
import com.czy.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author czy
 * @version 1.0.1
 * @company 东方标准
 * @date 2020/1/7 14:52
 * @description
 */

@Controller
public class MainController {

    @Autowired
    SysUserService userService;

    @RequestMapping("toLogin")
    public String login() {
        return "/login";
    }

    @RequestMapping("doLogin")
    @ResponseBody
    public Result doLogin(@RequestBody Map<String, Object> params, HttpSession session) {
        Result result = new Result();

        String code = params.get("code").toString();
        String vcode = session.getAttribute("vcode").toString();
        if (code.equals(vcode)) {

            SysUser user = new SysUser();
            user.setUsername(params.get("userName").toString());
            user.setPassword(params.get("password").toString());
            SysUser checkUser = userService.checkSysUser(user);
            if (checkUser != null) {
                result.setSuccess(true);
                result.setObj(checkUser);
                result.setMsg("登录成功");
                session.setAttribute("userInfo", checkUser);
            }
        }

        return result;
    }

    @RequestMapping("logout")
    @ResponseBody
    public Result logout(HttpSession session){
        Result result = new Result();
        session.invalidate();
        result.setSuccess(true);
        return result;
    }

    @RequestMapping("index")
    public ModelAndView index() {
        return new ModelAndView("/index");
    }
}
