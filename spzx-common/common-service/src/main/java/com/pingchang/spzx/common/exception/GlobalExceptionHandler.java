package com.pingchang.spzx.common.exception;

import com.pingchang.spzx.model.vo.common.Result;
import com.pingchang.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 命祌紸顁
 * @Date: 2023/10/19  10:23
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    public Result error() {
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }


    //自定义异常处理
    @ExceptionHandler(GuiguException.class)
    @ResponseBody
    public Result error(GuiguException e) {
        return Result.build(null, e.getResultCodeEnum());
    }
}
