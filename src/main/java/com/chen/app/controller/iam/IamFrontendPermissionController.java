package com.chen.app.controller.iam;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import com.diboot.core.controller.BaseCrudRestController;
import com.diboot.core.service.DictionaryService;
import com.diboot.core.util.BeanUtils;
import com.diboot.core.util.V;
import com.diboot.core.vo.*;
import com.diboot.iam.annotation.BindPermission;
import com.diboot.iam.annotation.Operation;
import com.diboot.iam.annotation.process.ApiPermissionCache;
import com.diboot.iam.config.Cons;
import com.diboot.iam.dto.IamFrontendPermissionDTO;
import com.diboot.iam.entity.IamFrontendPermission;
import com.diboot.iam.service.IamFrontendPermissionService;
import com.diboot.iam.vo.IamFrontendPermissionListVO;
import com.diboot.iam.vo.IamFrontendPermissionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
* 前端资源权限相关Controller
* @author 陈永鹏
* @version 1.0.0
* @date 2020-06-08
* Copyright © hcr
*/
@RestController
@RequestMapping("/iam/frontendPermission")
@Slf4j
@Api(tags = {"前端资源权限"})
@BindPermission(name = "前端资源权限")
public class IamFrontendPermissionController extends BaseCrudRestController<IamFrontendPermission, IamFrontendPermissionVO> {

    @Autowired
    private IamFrontendPermissionService iamFrontendPermissionService;

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
    @BindPermission(name = "查看列表", code = Operation.LIST)
    @GetMapping("/list")
    public JsonResult getViewObjectListMapping(IamFrontendPermission entity, HttpServletRequest request) throws Exception{
        QueryWrapper<IamFrontendPermission> queryWrapper = super.buildQueryWrapper(entity, request);
        queryWrapper.lambda().orderByDesc(IamFrontendPermission::getSortId, IamFrontendPermission::getId);
        List<IamFrontendPermissionListVO> voList = iamFrontendPermissionService.getViewObjectList(queryWrapper, null, IamFrontendPermissionListVO.class);
        voList = BeanUtils.buildTree(voList);
        return JsonResult.OK(voList);
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
    * 新建菜单项、按钮/权限列表
    * @param iamFrontendPermissionDTO
    * @param request
    * @return
    * @throws Exception
    */
    @ApiOperation(value = "新建数据")
    @BindPermission(name = "新建", code = Operation.CREATE)
    @PostMapping("/")
    public JsonResult createEntityMapping(@Valid @RequestBody IamFrontendPermissionDTO iamFrontendPermissionDTO, HttpServletRequest request) throws Exception {
        iamFrontendPermissionService.createMenuAndPermissions(iamFrontendPermissionDTO);
        return JsonResult.OK();
    }

    /***
    * 更新用户、账号和用户角色关联列表
    * @param iamFrontendPermissionDTO
    * @return JsonResult
    * @throws Exception
    */
    @ApiOperation(value = "根据ID更新数据")
    @PutMapping("/{id}")
    @BindPermission(name = "更新", code = Operation.UPDATE)
    public JsonResult updateEntityMapping(@PathVariable("id") Long id, @Valid @RequestBody IamFrontendPermissionDTO iamFrontendPermissionDTO, HttpServletRequest request) throws Exception {
        iamFrontendPermissionService.updateMenuAndPermissions(iamFrontendPermissionDTO);
        return JsonResult.OK();
    }

    /***
    * 删除用户、账号和用户角色关联列表
    * @param id
    * @return
    * @throws Exception
    */
    @ApiOperation(value = "根据ID删除数据")
    @DeleteMapping("/{id}")
    @BindPermission(name = "删除", code = Operation.DELETE)
    public JsonResult deleteEntityMapping(@PathVariable("id")Long id, HttpServletRequest request) throws Exception {
        iamFrontendPermissionService.deleteMenuAndPermissions(id);
        return JsonResult.OK();
    }

    /***
    * 加载更多数据
    * @return
    * @throws Exception
    */
    @ApiOperation(value = "获取更多关联数据")
    @GetMapping("/attachMore")
    public JsonResult attachMore(HttpServletRequest request, ModelMap modelMap) throws Exception{
        // 获取关联表数据IamFrontendPermission的树状列表
        List<IamFrontendPermissionListVO> menuList = iamFrontendPermissionService.getViewObjectList(
            Wrappers.<IamFrontendPermission>lambdaQuery().eq(IamFrontendPermission::getDisplayType, Cons.FRONTEND_PERMISSION_DISPLAY_TYPE.MENU.name()),
            null,
            IamFrontendPermissionListVO.class
        );
        menuList = BeanUtils.buildTree(menuList);
        modelMap.put("menuList", menuList);
        // 获取关联数据字典USER_STATUS的KV
        List<KeyValue> frontendPermissionCodeKvList = dictionaryService.getKeyValueList(Cons.DICTTYPE.FRONTEND_PERMISSION_CODE.name());
        modelMap.put("frontendPermissionCodeKvList", frontendPermissionCodeKvList);
        return JsonResult.OK(modelMap);
    }

    /**
    * 列表排序
    * @param permissionList
    * @param request
    * @return
    * @throws Exception
    */
    @ApiOperation(value = "更新列表排序")
    @PostMapping("/sortList")
    @BindPermission(name="列表排序", code = Operation.UPDATE)
    public JsonResult sortList(@RequestBody List<IamFrontendPermission> permissionList, HttpServletRequest request) throws Exception {
        iamFrontendPermissionService.sortList(permissionList);
        return JsonResult.OK().msg("更新成功");
    }

    /***
    * api接口列表（供前端选择）
    * @param request
    * @return
    * @throws Exception
    */
    @ApiOperation(value = "授权接口列表(前端备选)")
    @GetMapping("/apiList")
    public JsonResult apiList(HttpServletRequest request) throws Exception{
        return JsonResult.OK(ApiPermissionCache.getApiPermissionVoList());
    }

    /***
    * 检查菜单编码是否重复
    * @param id
    * @param code
    * @param request
    * @return
    */
    @ApiOperation(value = "检查编码是否重复")
    @GetMapping("/checkCodeDuplicate")
    public JsonResult checkCodeDuplicate(@RequestParam(required = false) Long id, @RequestParam String code, HttpServletRequest request) {
        if (V.notEmpty(code)) {
            LambdaQueryWrapper<IamFrontendPermission> wrapper = Wrappers.<IamFrontendPermission>lambdaQuery()
                .select(IamFrontendPermission::getId)
                .eq(IamFrontendPermission::getFrontendCode, code)
                .eq(IamFrontendPermission::getDisplayType, Cons.FRONTEND_PERMISSION_DISPLAY_TYPE.MENU.name());
            if (V.notEmpty(id)) {
                wrapper.ne(IamFrontendPermission::getId, id);
            }
            boolean exists = iamFrontendPermissionService.exists(wrapper);
            if (exists) {
                return JsonResult.FAIL_VALIDATION("编码已存在: "+code);
            }
        }
        return JsonResult.OK();
    }

}