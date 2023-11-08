package com.pingchang.spzx.manager.service;

import com.pingchang.spzx.model.entity.product.Category;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author: 命祌紸顁
 * @Date: 2023/11/6  16:24
 */
public interface CategoryService {
    List<Category> findCategoryList(Long id);

    void exportData(HttpServletResponse response);

    void importData(MultipartFile file);
}
