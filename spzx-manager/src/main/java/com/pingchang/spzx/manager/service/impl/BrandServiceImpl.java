package com.pingchang.spzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pingchang.spzx.manager.mapper.BrandMapper;
import com.pingchang.spzx.manager.service.BrandService;
import com.pingchang.spzx.model.entity.product.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 命祌紸顁
 * @Date: 2023/11/7  16:17
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public PageInfo<Brand> findByPage(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Brand> brandList = brandMapper.findByPage();
        PageInfo<Brand> pageInfo = new PageInfo<>(brandList);

        return pageInfo;
    }

    @Override
    public void save(Brand brand) {
        brandMapper.save(brand);
    }
}
