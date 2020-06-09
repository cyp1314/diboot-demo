package com.chen.app.service.impl;

import com.chen.app.excel.entity.Department;
import com.chen.app.mapper.DepartmentMapper;
import com.chen.app.service.DepartmentService;
import com.diboot.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 部门相关Service实现
 * @author www.dibo.ltd
 * @version v2.0
 * @date 2019/1/30
 */
@Service
public class DepartmentServiceImpl extends BaseServiceImpl<DepartmentMapper, Department> implements DepartmentService {

}