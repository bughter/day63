package com.czy.service.impl;

import com.czy.entity.AppVersion;
import com.czy.mapper.AppVersionMapper;
import com.czy.service.AppVersionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author czy
 * @version 1.0.1
 * @company 东方标准
 * @date 2019/12/23 19:01
 * @description
 */
@Service
@Transactional
public class AppVersionServiceImpl extends BaseServiceImpl<AppVersion> implements AppVersionService {

    @Autowired
    AppVersionMapper mapper;

    public PageInfo<AppVersion> getPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
//        List<AppVersion> list = mapper.selectAll();//当前方法返回值已经被替换成Page对象类型
//        PageInfo<AppVersion> pageInfo = new PageInfo<>(list);


        AppVersion appVersion = new AppVersion();
        appVersion.setDelFlag("0");
        List<AppVersion> list = mapper.select(appVersion);
        return new PageInfo<>(list);
    }

}
