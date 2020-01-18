package com.czy.service.impl;

import com.czy.entity.Examine;
import com.czy.mapper.ExamineMapper;
import com.czy.service.ExamineService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.deploy.net.proxy.pac.PACFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author czy
 * @version 1.0.1
 * @company 东方标准
 * @date 2019/12/26 15:37
 * @description
 */

@Service
public class ExamineServiceImpl extends BaseServiceImpl<Examine> implements ExamineService {


    @Autowired
    ExamineMapper examineMapper;


    @Override
    public PageInfo<Examine> getPageByCondition(Map<String, Object> params) {

        if (!params.containsKey("pageNum") || StringUtils.isEmpty(params.get("pageNum")))
            params.put("pageNum", 1);

        if (!params.containsKey("pageSize") || StringUtils.isEmpty(params.get("pageSize")))
            params.put("pageSize", 5);

        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage((int) params.get("pageNum"), (int) params.get("pageSize"));

        List<Examine> list=examineMapper.selectByCondition(params);
        return new PageInfo<>(list);
    }
}
