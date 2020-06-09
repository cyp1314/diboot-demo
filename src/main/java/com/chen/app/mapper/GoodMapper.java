package com.chen.app.mapper;

import com.diboot.core.mapper.BaseCrudMapper;
import com.chen.app.entity.Good;
import org.apache.ibatis.annotations.Mapper;

/**
* 商品信息表Mapper
* @author 陈永鹏
* @version 1.0.0
* @date 2020-06-08
 * Copyright © hcr
*/
@Mapper
public interface GoodMapper extends BaseCrudMapper<Good> {

}

