package com.chen.app.excel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.diboot.core.entity.BaseExtEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Department部门
 * @author www.dibo.ltd
 * @version v2.0
 * @date 2018/12/27
 */
@Getter
@Setter
@Accessors(chain = true)
public class Department extends BaseExtEntity {
    private static final long serialVersionUID = -4849732665419794547L;

    @TableField
    @NotNull(message = "parentId不能为空")
    private Long parentId;

    @TableField
    @NotNull(message = "单位ID不能为空")
    private Long orgId;

    @TableField
    @NotNull(message = "部门名称不能为空")
    @Length(min = 10, max = 20, message = "部门名称长度需>=10且<=20")
    private String name;

}