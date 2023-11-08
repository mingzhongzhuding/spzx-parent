package com.pingchang.spzx.common.exception;

import com.pingchang.spzx.model.vo.common.Result;
import com.pingchang.spzx.model.vo.common.ResultCodeEnum;
import lombok.Data;

import java.util.zip.InflaterInputStream;

/**
 * @Author: 命祌紸顁
 * @Date: 2023/10/19  10:28
 */
@Data
public class GuiguException extends RuntimeException{

    private Integer code;
    private String message;

    private ResultCodeEnum resultCodeEnum;

    public GuiguException( ResultCodeEnum resultCodeEnum) {
        this.resultCodeEnum = resultCodeEnum;
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }
}
