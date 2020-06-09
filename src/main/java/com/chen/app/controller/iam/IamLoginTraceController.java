package com.chen.app.controller.iam;

import com.diboot.core.controller.BaseCrudRestController;
import com.diboot.core.service.DictionaryService;
import com.diboot.core.vo.*;
import com.diboot.iam.annotation.BindPermission;
import com.diboot.iam.annotation.Operation;
import com.diboot.iam.config.Cons;
import com.diboot.iam.entity.IamLoginTrace;
import com.diboot.iam.vo.IamLoginTraceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
* 登录日志
* @author 陈永鹏
* @version 1.0.0
* @date 2020-06-08
* Copyright © hcr
*/
@RestController
@RequestMapping("/iam/loginTrace")
@Slf4j
@Api(tags = {"登录日志"})
@BindPermission(name = "登录日志")
public class IamLoginTraceController extends BaseCrudRestController<IamLoginTrace, IamLoginTraceVO> {

    @Autowired
    private DictionaryService dictionaryService;

    /***
    * 查询分页数据
    * @return
    * @throws Exception
    */
    @ApiOperation(value = "获取列表分页数据")
    @GetMapping("/list")
    @BindPermission(name = "查看列表", code = Operation.LIST)
    public JsonResult getViewObjectListMapping(IamLoginTrace entity, Pagination pagination, HttpServletRequest request) throws Exception{
        return super.getViewObjectList(entity, pagination, request);
    }

    /**
    * 加载更多数据
    * @return
    * @throws Exception
    */
    @ApiOperation(value = "获取更多关联数据")
    @GetMapping("/attachMore")
    public JsonResult attachMore(HttpServletRequest request, ModelMap modelMap) throws Exception {
        // 获取关联数据字典AUTH_TYPE的KV
        List<KeyValue> authTypeKvList = dictionaryService.getKeyValueList(Cons.DICTTYPE.AUTH_TYPE.name());
        modelMap.put("authTypeKvList", authTypeKvList);
        return JsonResult.OK(modelMap);
    }

}