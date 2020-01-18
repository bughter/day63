package com.czy.service;

import com.czy.entity.WorkOrder;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface WorkOrderService extends BaseService<WorkOrder> {

//    List<AppVersion> selectAll();

    PageInfo<WorkOrder> selectAll(Map<String, Object> params);

    //根据workOrder的id查询一个详单信息
    Map<String,Object> selectByOid(long oid);
}
