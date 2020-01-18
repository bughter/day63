package com.czy.service;

import com.czy.entity.AppVersion;
import com.github.pagehelper.PageInfo;


public interface AppVersionService extends BaseService<AppVersion>{

    PageInfo<AppVersion> getPage(Integer pageNum,Integer pageSize);

}
