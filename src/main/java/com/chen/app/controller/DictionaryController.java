package com.chen.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.diboot.core.util.V;
import com.diboot.core.vo.*;
import com.diboot.core.entity.Dictionary;
import com.diboot.core.service.DictionaryService;
import com.diboot.core.controller.BaseCrudRestController;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
* 数据字典相关Controller
* @author 陈永鹏
* @version 1.0.0
* @date 2020-06-08
* Copyright © hcr
*/
@Api(value = "数据字典")
@RestController
@RequestMapping("/dictionary")
@Slf4j
public class DictionaryController extends BaseCrudRestController<Dictionary, DictionaryVO> {
    @Autowired
    private DictionaryService dictionaryService;

    /***
    * 查询ViewObject的分页数据
    * <p>
    * url请求参数示例: /list?field=abc&pageSize=20&pageIndex=1&orderBy=id
    * </p>
    * @return
    * @throws Exception
    */
    @ApiOperation(value = "获取列表分页数据")
    @GetMapping("/list")
    public JsonResult getViewObjectListMapping(Dictionary entity, Pagination pagination, HttpServletRequest request) throws Exception{
        QueryWrapper<Dictionary> queryWrapper = super.buildQueryWrapper(entity, request);
        List<DictionaryVO> voList = dictionaryService.getViewObjectList(queryWrapper, pagination, DictionaryVO.class);
        return JsonResult.OK(voList).bindPagination(pagination);
    }

    /***
    * 根据资源id查询ViewObject
    * @param id ID
    * @return
    * @throws Exception
    */
    @ApiOperation(value = "根据ID获取详情数据")
    @GetMapping("/{id}")
    public JsonResult getViewObjectMapping(@PathVariable("id") Long id, HttpServletRequest request) throws Exception{
        return super.getViewObject(id, request);
    }

    /**
    * 创建资源对象
    * @param entityVO
    * @return JsonResult
    * @throws Exception
    */
    @ApiOperation(value = "新建数据")
    @PostMapping("/")
    public JsonResult createEntityMapping(@RequestBody @Valid DictionaryVO entityVO, HttpServletRequest request) throws Exception {
        boolean success = dictionaryService.createDictAndChildren(entityVO);
        if(!success){
            return JsonResult.FAIL_OPERATION("保存数据字典失败！");
        }
        return JsonResult.OK();
    }

    /***
    * 根据ID更新资源对象
    * @param entityVO
    * @return JsonResult
    * @throws Exception
    */
    @ApiOperation(value = "根据ID更新数据")
    @PutMapping("/{id}")
    public JsonResult updateEntityMapping(@PathVariable("id")Long id, @Valid @RequestBody DictionaryVO entityVO, HttpServletRequest request) throws Exception {
        entityVO.setId(id);
        boolean success = dictionaryService.updateDictAndChildren(entityVO);
        if(!success){
            return JsonResult.FAIL_OPERATION("更新数据字典失败！");
        }
        return JsonResult.OK();
    }

    /***
    * 根据id删除资源对象
    * @param id
    * @return
    * @throws Exception
    */
    @ApiOperation(value = "根据ID删除数据")
    @DeleteMapping("/{id}")
    public JsonResult deleteEntityMapping(@PathVariable("id")Long id, HttpServletRequest request) throws Exception {
        boolean success = dictionaryService.deleteDictAndChildren(id);
        if(!success){
            return JsonResult.FAIL_OPERATION("删除数据字典失败！");
        }
        return JsonResult.OK();
    }

    /***
    * 获取数据字典数据列表
    * @param type
    * @return
    * @throws Exception
    */
    @ApiOperation(value = "根据type获取数据字典子项")
    @GetMapping("/items/{type}")
    public JsonResult getItems(@PathVariable("type")String type) throws Exception{
        if (V.isEmpty(type)){
            return JsonResult.FAIL_INVALID_PARAM("type参数未指定");
        }
        List<KeyValue> itemsList = dictionaryService.getKeyValueList(type);
        return JsonResult.OK(itemsList);
    }

    /**
    * 校验类型编码是否重复
    * @param id
    * @param type
    * @param request
    * @return
    */
    @ApiOperation(value = "检查type是否重复")
    @GetMapping("/checkTypeDuplicate")
    public JsonResult checkTypeDuplicate(@RequestParam(required = false) Long id, @RequestParam String type, HttpServletRequest request) {
        if (V.notEmpty(type)) {
            LambdaQueryWrapper<Dictionary> wrapper = new LambdaQueryWrapper();
            wrapper.select(Dictionary::getId).eq(Dictionary::getType, type).eq(Dictionary::getParentId, 0);
            if (V.notEmpty(id)) {
                wrapper.ne(Dictionary::getId, id);
            }
            boolean alreadyExists = dictionaryService.exists(wrapper);
            if (alreadyExists) {
                return new JsonResult(Status.FAIL_OPERATION, "类型编码已存在");
            }
        }
        return JsonResult.OK();
    }

}