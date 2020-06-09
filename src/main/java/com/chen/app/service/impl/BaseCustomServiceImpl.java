package com.chen.app.service.impl;

import com.diboot.core.service.impl.BaseServiceImpl;
import com.diboot.core.mapper.BaseCrudMapper;
import com.chen.app.service.BaseCustomService;
import lombok.extern.slf4j.Slf4j;

/**
* 自定义BaseService接口实现
* @author 陈永鹏
* @version 1.0.0
* @date 2020-06-08
 * Copyright © hcr
*/
@Slf4j
public class BaseCustomServiceImpl<M extends BaseCrudMapper<T>, T> extends BaseServiceImpl<M, T> implements BaseCustomService<T> {

}
