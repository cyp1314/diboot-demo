package com.chen.app.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.diboot.core.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
* 自定义BaseEntity，对diboot-core的BaseEntity做差异化定义
* @author 陈永鹏
* @version 1.0.0
* @date 2020-06-08
* Copyright © hcr
*/
@Getter @Setter @Accessors(chain = true)
public class BaseCustomEntity extends BaseEntity {
    private static final long serialVersionUID = 8064056727957924053L;

    @ApiModelProperty(hidden = true)
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(hidden = true)
    @JSONField(serialize = false)
    @TableField("is_deleted")
    private boolean deleted = false;

}
