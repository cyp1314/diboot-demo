package com.chen.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.diboot.file.controller.BaseFileController;
import com.diboot.file.entity.UploadFile;
import com.diboot.file.util.HttpHelper;
import com.diboot.core.entity.Dictionary;
import com.diboot.core.vo.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
* 文件上传下载相关Controller
* @author 陈永鹏
* @version 1.0.0
* @date 2020-06-08
* Copyright © hcr
*/
@Api(tags = {"文件上传下载"})
@RestController
@RequestMapping("/uploadFile")
@Slf4j
public class UploadFileController extends BaseFileController {

    /**
     * 上传文件
     **/
    @ApiOperation(value = "上传文件")
    @PostMapping("/upload")
    public JsonResult uploadMapping(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception{
        return super.uploadFile(file, Dictionary.class, request);
    }

    /**
     * 下载文件
     **/
    @ApiOperation(value = "上传下载")
    @GetMapping("/download/{fileUuid}")
    public JsonResult downloadMapping(@PathVariable("fileUuid")String fileUuid, HttpServletResponse response) throws Exception {
        UploadFile uploadFile = uploadFileService.getEntity(fileUuid);
        if(uploadFile == null){
            return new JsonResult(Status.FAIL_VALIDATION, "文件不存在");
        }
        // 下载
        HttpHelper.downloadLocalFile(uploadFile.getStoragePath(), "导出文件.txt", response);
        return null;
    }

    /**
     * 上传文件列表
     */
    @ApiOperation(value = "上传文件的列表")
    @GetMapping("/list")
    public JsonResult getViewObjectListMapping(UploadFile uploadFile, Pagination pagination, HttpServletRequest request) throws Exception{
        QueryWrapper<UploadFile> queryWrapper = super.buildQueryWrapper(uploadFile, request);
        return super.getEntityListWithPaging(queryWrapper, pagination);
    }

}
