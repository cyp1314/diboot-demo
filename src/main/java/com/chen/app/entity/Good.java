package com.chen.app.entity;

import java.util.Date;
import java.lang.Double;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.diboot.core.binding.query.BindQuery;
import com.diboot.core.binding.query.Comparison;
import com.diboot.core.util.D;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
* 商品信息表 Entity定义
* @author 陈永鹏
* @version 1.0.0
* @date 2020-06-08
* Copyright © hcr
*/
@ApiModel(value = "商品信息表")
@Getter @Setter @Accessors(chain = true)
public class Good extends BaseExtCustomEntity {
    private static final long serialVersionUID = 2056703335856018052L;

    // 商品名称
    @ApiModelProperty(value="商品名称", example = "")
    @Length(max=100, message="商品名称长度应小于100")
    @TableField()
    private String name;

    // 商品价格
    @ApiModelProperty(value="商品价格", example = "")
    @Length(max=100, message="商品价格长度应小于100")
    @TableField()
    private String price;

    // 创建人
    @ApiModelProperty(value="创建人", example = "0")
    @TableField()
    private Long createBy;

    // 更新时间
    @ApiModelProperty(value="更新时间", example = "2020-06-08 14:49")
    @JSONField(format = D.FORMAT_DATETIME_Y4MDHM)
    @TableField(insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NOT_NULL)
    private Date updateTime;

}
