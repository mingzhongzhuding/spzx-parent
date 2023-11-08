package com.pingchang.spzx.manager.controller;

import com.pingchang.spzx.manager.service.CategoryService;
import com.pingchang.spzx.model.entity.product.Category;
import com.pingchang.spzx.model.vo.common.Result;
import com.pingchang.spzx.model.vo.common.ResultCodeEnum;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author: 命祌紸顁
 * @Date: 2023/11/6  16:23
 */
@RestController
@RequestMapping("/admin/product/category")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @GetMapping("/findCategoryList/{id}")
    public Result findCategoryList(@PathVariable Long id) {
        List<Category> list =  categoryService.findCategoryList(id);
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/exportData")
    public void exportData(HttpServletResponse response) {
        categoryService.exportData(response);
    }

    @PostMapping("/importData")
    public Result importData(MultipartFile file) {
        categoryService.importData(file);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
