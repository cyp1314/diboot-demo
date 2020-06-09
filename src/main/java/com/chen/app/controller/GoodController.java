package com.chen.app.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.diboot.core.vo.*;
import com.diboot.iam.annotation.Operation;
import com.diboot.iam.annotation.BindPermission;
import com.chen.app.entity.Good;
import com.chen.app.service.GoodService;
import com.chen.app.vo.GoodVO;

import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
* 商品信息表 相关Controller
* @author 陈永鹏
* @version 1.0.0
* @date 2020-06-08
* Copyright © hcr
*/
@Api(tags = {"商品信息表"})
@RestController
@RequestMapping("/good")
@BindPermission(name = "商品信息表")
@Slf4j
public class GoodController extends BaseCustomCrudRestController<Good, GoodVO> {
    @Autowired
    private GoodService goodService;

    /***
    * 查询ViewObject的分页数据
    * <p>
    * url请求参数示例: /list?field=abc&pageSize=20&pageIndex=1&orderBy=id
    * </p>
    * @return
    * @throws Exception
    */
    @ApiOperation(value = "获取列表分页数据")
    @BindPermission(name = "查看列表", code = Operation.LIST)
    @GetMapping("/list")
    public JsonResult getViewObjectListMapping(Good entity, Pagination pagination, HttpServletRequest request) throws Exception{
        return super.getViewObjectList(entity, pagination, request);
    }

    /***
    * 根据资源id查询ViewObject
    * @param id ID
    * @return
    * @throws Exception
    */
    @ApiOperation(value = "根据ID获取详情数据")
    @BindPermission(name = "查看详情", code = Operation.DETAIL)
    @GetMapping("/{id}")
    public JsonResult getViewObjectMapping(@PathVariable("id")Long id, HttpServletRequest request) throws Exception{
        return super.getViewObject(id, request);
    }

    /***
    * 创建资源对象
    * @param entity
    * @return JsonResult
    * @throws Exception
    */
    @ApiOperation(value = "新建数据")
    @BindPermission(name = "新建", code = Operation.CREATE)
    @PostMapping("/")
    public JsonResult createEntityMapping(@Valid @RequestBody Good entity, HttpServletRequest request) throws Exception {
        return super.createEntity(entity, request);
    }

    /***
    * 根据ID更新资源对象
    * @param entity
    * @return JsonResult
    * @throws Exception
    */
    @ApiOperation(value = "根据ID更新数据")
    @BindPermission(name = "更新", code = Operation.UPDATE)
    @PutMapping("/{id}")
    public JsonResult updateEntityMapping(@PathVariable("id")Long id, @Valid @RequestBody Good entity, HttpServletRequest request) throws Exception {
        return super.updateEntity(id, entity, request);
    }

    /***
    * 根据id删除资源对象
    * @param id
    * @return
    * @throws Exception
    */
    @ApiOperation(value = "根据ID删除数据")
    @BindPermission(name = "删除", code = Operation.DELETE)
    @DeleteMapping("/{id}")
    public JsonResult deleteEntityMapping(@PathVariable("id")Long id, HttpServletRequest request) throws Exception {
        return super.deleteEntity(id, request);
    }

}