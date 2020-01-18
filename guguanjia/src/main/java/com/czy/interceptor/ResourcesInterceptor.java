package com.czy.interceptor;

import com.czy.entity.SysResource;
import com.czy.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author czy
 * @version 1.0.1
 * @company 东方标准
 * @date 2020/1/13 19:18
 * @description
 */
public class ResourcesInterceptor implements HandlerInterceptor {

    @Autowired
    SysResourceService resourceService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        List<SysResource> sysResources = resourceService.selectAll();
        boolean flag = false;
        for (SysResource sr : sysResources) {
            if (!StringUtils.isEmpty(sr.getUrl()) && uri.contains(sr.getUrl())) {
                flag=true;
            }
        }

        if(!flag){
            return true;
        }
        else{
            List<SysResource> resources = (List<SysResource>) request.getSession().getAttribute("resources");
            for (SysResource resource : resources) {
                if(!StringUtils.isEmpty(resource.getUrl())&&uri.contains(resource.getUrl())){
                    return true;
                }
            }
        }
        request.getRequestDispatcher("/index").forward(request,response);//没有权限，跳转到index
        return false;
    }


}
