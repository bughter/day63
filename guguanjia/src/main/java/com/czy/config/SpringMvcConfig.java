package com.czy.config;

import com.czy.aspect.LogAspect;
import com.czy.interceptor.LoginInterceptor;
import com.czy.interceptor.ResourcesInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author czy
 * @version 1.0.1
 * @company 东方标准
 * @date 2019/12/23 17:37
 * @description 开启mvc的配置
 */
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy //开启切面注解支持
@ComponentScan(basePackages = "com.czy.controller")
public class SpringMvcConfig implements WebMvcConfigurer {


    /**
     * 由于spring的生命周期中，@Bean创建组件bean会先执行
     * 依赖注入操作会后执行，可以从容器中获取ResourcesInteceptor对象
     */
    @Autowired
    ResourcesInterceptor resourcesInterceptor;

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public InternalResourceViewResolver getViewResolver(){
        return new InternalResourceViewResolver("/WEB-INF/html",".html");
    }

    //配置文件上传解析器  必须指定bean的命名
    @Bean("multipartResolver")
    public CommonsMultipartResolver getMultipartResolver(){
        return new CommonsMultipartResolver();
    }
    @Bean
    public ResourcesInterceptor getResourcesInteceptor(){

        return new ResourcesInterceptor();
    }


    /**
     * 注册拦截器设置
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        //注册拦截器对象
        InterceptorRegistration loginRegistration = registry.addInterceptor(loginInterceptor);
        //设置拦截逻辑
        loginRegistration.addPathPatterns(new String[]{"/**"});//拦截所有请求
        //设置放行逻辑
        loginRegistration.excludePathPatterns(new String[]{"/toLogin","/doLogin","/index","/manager/menu/selectByUid"});
        loginRegistration.order(1);

        InterceptorRegistration resourcesRegistration = registry.addInterceptor(resourcesInterceptor);
        //设置拦截逻辑
        resourcesRegistration.addPathPatterns(new String[]{"/**"});//拦截所有请求
        resourcesRegistration.excludePathPatterns(new String[]{"/toLogin","/doLogin","/index","/manager/menu/selectByUid"});
        resourcesRegistration.order(2);


    }

    @Bean
    public LogAspect getLogAspect(){
        return new LogAspect();
    }
}
