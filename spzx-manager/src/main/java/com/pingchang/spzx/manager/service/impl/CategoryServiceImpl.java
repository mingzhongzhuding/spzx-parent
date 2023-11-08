package com.pingchang.spzx.manager.service.impl;

import com.alibaba.excel.EasyExcel;
import com.pingchang.spzx.common.exception.GuiguException;
import com.pingchang.spzx.manager.listener.ExcelListener;
import com.pingchang.spzx.manager.mapper.CategoryMapper;
import com.pingchang.spzx.manager.service.CategoryService;
import com.pingchang.spzx.model.entity.product.Category;
import com.pingchang.spzx.model.vo.common.ResultCodeEnum;
import com.pingchang.spzx.model.vo.product.CategoryExcelVo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 命祌紸顁
 * @Date: 2023/11/6  16:24
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findCategoryList(Long id) {

        //1 根据id条件值进行查询，返回list集合
        // SELECT * FROM category WHERE parent_id=id
        List<Category> categoryList = categoryMapper.selectCategoryByParentId(id);

        //2 遍历返回list集合，
        // 判断每个分类是否有下一层分类，如果有设置 hasChildren = true
        if(!CollectionUtils.isEmpty(categoryList)) {
            categoryList.forEach(category -> {
                // 判断每个分类是否有下一层分类
                //SELECT count(*) FROM category WHERE parent_id=1
                int count = categoryMapper.selectCountByParentId(category.getId());
                if(count > 0) {//下一层分类
                    category.setHasChildren(true);
                } else {
                    category.setHasChildren(false);
                }
            });
        }
        return categoryList;
    }

    @Override
    public void exportData(HttpServletResponse response) {

        try {
            //1 设置响应头信息和其他信息
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");


            List<CategoryExcelVo> categoryExcelVoList = new ArrayList<>();



            String fileName = URLEncoder.encode("分类数据", "utf-8");
            response.setHeader("Content-disposition","attachment:filename="+fileName);
            List<Category> categoryList = categoryMapper.findAll();
            //response.setHeader("Content-disposition","attachment;filename="+fileName+".xlsx");
            categoryList.forEach(category -> {
                CategoryExcelVo categoryExcelVo = new CategoryExcelVo();
                BeanUtils.copyProperties(category,categoryExcelVo);
                categoryExcelVoList.add(categoryExcelVo);
            });


            EasyExcel.write(response.getOutputStream(), CategoryExcelVo.class)
                    .sheet("分类数据").doWrite(categoryExcelVoList);

        } catch (Exception e) {

        }




    }

    /*    @Override
    public void exportData(HttpServletResponse response) {
        try {
            //1 设置响应头信息和其他信息
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");

            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("分类数据", "UTF-8");

            //设置响应头信息 Content-disposition
            response.setHeader("Content-disposition","attachment;filename="+fileName+".xlsx");

            //2 调用mapper方法查询所有分类，返回list集合
            List<Category> categoryList = categoryMapper.findAll();

            //最终数据list集合
            List<CategoryExcelVo> categoryExcelVoList = new ArrayList<>();
            // List<Category>  --  List<CategoryExcelVo>
            for(Category category : categoryList) {
                CategoryExcelVo categoryExcelVo = new CategoryExcelVo();
                //把category值获取出来，设置到categoryExcelVo里面
//                Long id = category.getId();
//                categoryExcelVo.setId(id);
                BeanUtils.copyProperties(category,categoryExcelVo);
                categoryExcelVoList.add(categoryExcelVo);
            }

            //3 调用EasyExcel的write方法完成写操作
            EasyExcel.write(response.getOutputStream(), CategoryExcelVo.class)
                    .sheet("分类数据").doWrite(categoryExcelVoList);
        }catch (Exception e) {
            e.printStackTrace();
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }
    }*/

    @Override
    public void importData(MultipartFile file) {

        ExcelListener<Category> excelListener = new ExcelListener<>(categoryMapper);
        try {
            EasyExcel.read(file.getInputStream(), CategoryExcelVo.class, excelListener).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }
    }
}
