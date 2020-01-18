package com.czy.service.impl;

import com.czy.entity.AppVersion;
import com.czy.entity.Statute;
import com.czy.service.StatuteService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author czy
 * @version 1.0.1
 * @company 东方标准
 * @date 2019/12/30 16:18
 * @description
 */
@Service
public class StatuteServiceImpl extends BaseServiceImpl<Statute> implements StatuteService {


    public PageInfo<Statute> getPage(Map<String, Object> params) {

        if (!params.containsKey("pageNum") || StringUtils.isEmpty(params.get("pageNum"))) {
            params.put("pageNum", 1);
        }

        if (!params.containsKey("pageSize") || StringUtils.isEmpty(params.get("pageSize"))) {
            params.put("pageSize", 5);
        }
        PageHelper.startPage((int) params.get("pageNum"), (int) params.get("pageSize"));

        Statute statute = new Statute();
        statute.setDelFlag("0");
        if (params.containsKey("type") && !StringUtils.isEmpty(params.get("type"))) {
            statute.setType( Integer.parseInt(params.get("type").toString()));
        }
        List<Statute> list = mapper.select(statute);
        return new PageInfo<>(list);
    }
}
