package com.pingchang.spzx.manager.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.ListUtils;
import com.pingchang.spzx.manager.mapper.CategoryMapper;
import com.pingchang.spzx.model.vo.product.CategoryExcelVo;

import java.util.List;

/**
 * @Author: 命祌紸顁
 * @Date: 2023/11/7  14:55
 */
public class ExcelListener<T> extends AnalysisEventListener<T> {

    private CategoryMapper categoryMapper;

    public ExcelListener(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;

    }

    private static final int BATCH_COUNT = 100;
    private List<T> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        cachedDataList.add(t);
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    private void saveData() {
        categoryMapper.batchInsert((List<CategoryExcelVo>) cachedDataList);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();

    }
}
