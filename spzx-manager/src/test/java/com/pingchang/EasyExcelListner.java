package com.pingchang;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 命祌紸顁
 * @Date: 2023/11/7  10:55
 */
public class EasyExcelListner<T> extends AnalysisEventListener<T> {

    private List<T> data = new ArrayList<>();

    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        data.add(t);
    }

    public List getData() {
        return data;
    }



    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
