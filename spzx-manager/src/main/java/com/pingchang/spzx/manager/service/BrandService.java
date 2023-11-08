package com.pingchang.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.pingchang.spzx.model.entity.product.Brand;

/**
 * @Author: 命祌紸顁
 * @Date: 2023/11/7  16:17
 */
public interface BrandService {
    PageInfo<Brand> findByPage(Integer page, Integer limit);

    void save(Brand brand);
}
