package com.pingchang.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.pingchang.spzx.manager.mapper.BrandMapper;
import com.pingchang.spzx.manager.service.BrandService;
import com.pingchang.spzx.model.entity.product.Brand;
import com.pingchang.spzx.model.vo.common.Result;
import com.pingchang.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 命祌紸顁
 * @Date: 2023/11/7  16:13
 */
@RestController
@RequestMapping("/admin/product/brand")
@Transactional
public class BrandController {

    @Autowired
    private BrandService brandService;


    @GetMapping("/{page}/{limit}")
    public Result list(@PathVariable Integer page,
                       @PathVariable Integer limit) {

        PageInfo<Brand> pageInfo =  brandService.findByPage(page, limit);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);

    }

    @PostMapping("/save")
    public Result save(@RequestBody Brand brand) {
        brandService.save(brand);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
