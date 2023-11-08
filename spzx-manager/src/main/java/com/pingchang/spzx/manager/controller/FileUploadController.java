package com.pingchang.spzx.manager.controller;

import com.pingchang.spzx.manager.service.FileUploadService;
import com.pingchang.spzx.model.vo.common.Result;
import com.pingchang.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 命祌紸顁
 * @Date: 2023/11/2  14:53
 */
@RestController
@RequestMapping("/admin/system")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/fileUpload")
    public Result fileUpload(MultipartFile file) {

        String url = fileUploadService.upload(file);
        return Result.build(url, ResultCodeEnum.SUCCESS);
    }
}
