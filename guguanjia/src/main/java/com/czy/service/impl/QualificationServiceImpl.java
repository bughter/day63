package com.czy.service.impl;

import com.czy.entity.Qualification;
import com.czy.mapper.QualificationMapper;
import com.czy.service.QualificationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author czy
 * @version 1.0.1
 * @company 东方标准
 * @date 2019/12/25 16:27
 * @description
 */

@Service
public class QualificationServiceImpl extends BaseServiceImpl<Qualification> implements QualificationService {


    @Autowired
    QualificationMapper qualificationMapper;

    @Override
    public PageInfo<Qualification> getPage(Map<String, Object> params) {

        if (!params.containsKey("pageNum") && StringUtils.isEmpty(params.get("pageNum"))) {
            params.put("pageNum", 1);
        }
        if (!params.containsKey("pageSize") && StringUtils.isEmpty(params.get("pageSize"))) {
            params.put("pageSize", 5);
        }

        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage((int) params.get("pageNum"), (int) params.get("pageSize"));
        List<Qualification> list = qualificationMapper.selectByCondition(params);
        return new PageInfo<>(list);
    }
}
