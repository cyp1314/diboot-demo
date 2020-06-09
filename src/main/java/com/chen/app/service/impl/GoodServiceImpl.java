package com.chen.app.service.impl;

import com.diboot.core.service.impl.BaseServiceImpl;
import com.chen.app.entity.Good;
import com.chen.app.mapper.GoodMapper;
import com.chen.app.service.GoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
* 商品信息表相关Service实现
* @author 陈永鹏
* @version 1.0.0
* @date 2020-06-08
 * Copyright © hcr
*/
@Service
@Slf4j
public class GoodServiceImpl extends BaseCustomServiceImpl<GoodMapper, Good> implements GoodService {

}
