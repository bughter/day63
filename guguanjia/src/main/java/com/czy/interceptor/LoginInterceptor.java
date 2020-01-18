package com.czy.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author czy
 * @version 1.0.1
 * @company 东方标准
 * @date 2020/1/13 19:17
 * @description
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object userInfo = session.getAttribute("userInfo");
        if(userInfo!=null){
            return true;
        }
        request.getRequestDispatcher("/toLogin").forward(request,response);//未登录
        return false;
    }
}
