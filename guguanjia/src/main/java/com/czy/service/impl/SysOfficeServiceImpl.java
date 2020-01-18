package com.czy.service.impl;

import com.czy.entity.SysOffice;
import com.czy.mapper.SysOfficeMapper;
import com.czy.service.SysOfficeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author czy
 * @version 1.0.1
 * @company 东方标准
 * @date 2019/12/30 9:52
 * @description
 */
@Service
@CacheConfig(cacheNames = "sysOfficeCache")
@Transactional
public class SysOfficeServiceImpl extends BaseServiceImpl<SysOffice> implements SysOfficeService {

    @Autowired
    SysOfficeMapper mapper;

    @Cacheable(key = "'com.czy.service.impl.SysOfficeServiceImpl:selectAll'")
    @Override
    public List<SysOffice> selectAll() {
        return super.selectAll();
    }


    @CacheEvict(allEntries = true)
    @Override
    public int updateByPrimaryKeySelective(SysOffice record) {
        return super.updateByPrimaryKeySelective(record);
    }


    @Override
    public PageInfo<SysOffice> getPage(Map<String, Object> params) {

        if (!params.containsKey("pageNum") || StringUtils.isEmpty(params.get("pageNum"))) {
            params.put("pageNmu", 1);
        }
        if (!params.containsKey("pageSize") || StringUtils.isEmpty(params.get("pageSize"))) {
            params.put("pageSize", 5);
        }
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage((int) params.get("pageNum"), (int) params.get("pageSize"));
        List<SysOffice> list = mapper.getPageByCondition(params);
        return new PageInfo<SysOffice>(list);
    }

    @Override
    public SysOffice selectByOid(long oid){
        return mapper.selectByOid(oid);
    }

    @Override
    public List<SysOffice> selectByRid(long rid){
        return mapper.selectByRid(rid);
    }
}
