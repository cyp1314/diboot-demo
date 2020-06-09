package com.chen.app.vo;

import com.diboot.core.binding.annotation.*;
import com.chen.app.entity.Good;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
* 商品信息表 VO定义
* @author 陈永鹏
* @version 1.0.0
* @date 2020-06-08
 * Copyright © hcr
*/
@Getter @Setter @Accessors(chain = true)
public class GoodVO extends Good  {
    private static final long serialVersionUID = -7308217801535745062L;

}