package com.pingchang;

import com.alibaba.excel.EasyExcel;
import com.pingchang.spzx.model.vo.product.CategoryExcelVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 命祌紸顁
 * @Date: 2023/11/7  10:54
 */
public class EasyExcelTest {
    public static void main(String[] args) {
        //read();
        write();
    }

    public static void read() {
        EasyExcelListner<CategoryExcelVo> easyExcelListner = new EasyExcelListner<>();

        String filename = "F://test//01.xlsx";
        EasyExcel.read(filename, CategoryExcelVo.class,easyExcelListner)
                .sheet().doRead();
        List data = easyExcelListner.getData();
        System.out.println(data);
    }


    public static void write() {
        List<CategoryExcelVo> list = new ArrayList<>();
        list.add(new CategoryExcelVo(1L , "数码办公" , "",0L, 1, 1)) ;
        list.add(new CategoryExcelVo(11L , "华为手机" , "",1L, 1, 2)) ;
        EasyExcel.write("F://test//02.xlsx",CategoryExcelVo.class)
                .sheet("分类数据").doWrite(list);
    }
}
