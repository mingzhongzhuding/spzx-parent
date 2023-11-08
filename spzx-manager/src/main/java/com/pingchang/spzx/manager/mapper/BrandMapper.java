package com.pingchang.spzx.manager.mapper;

import com.pingchang.spzx.model.entity.product.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: 命祌紸顁
 * @Date: 2023/11/7  16:20
 */
@Mapper
public interface BrandMapper {

    List<Brand> findByPage();

    void save(Brand brand);
}
