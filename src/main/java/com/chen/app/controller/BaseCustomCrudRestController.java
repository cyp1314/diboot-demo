package com.chen.app.controller;

import com.diboot.core.controller.BaseCrudRestController;
import com.diboot.core.entity.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import java.io.Serializable;

/**
* 自定义通用CRUD父类RestController
* @author 陈永鹏
* @version 1.0.0
* @date 2020-06-08
* Copyright © hcr
*/
@Slf4j
public class BaseCustomCrudRestController<E extends BaseEntity, VO extends Serializable> extends BaseCrudRestController {

}