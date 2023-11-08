package com.pingchang.spzx.manager.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 命祌紸顁
 * @Date: 2023/11/2  14:54
 */
public interface FileUploadService {
    String upload(MultipartFile file);
}
