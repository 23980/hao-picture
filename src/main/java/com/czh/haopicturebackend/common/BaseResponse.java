package com.czh.haopicturebackend.common;

import com.czh.haopicturebackend.exception.ErrorCode;
import lombok.Data;

/**
 * 通用响应类
 */
@Data
public class BaseResponse<T> {

    private int code;

    private T data;

    private String message;

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(int code, T data) {
        this(code, data, "");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    }



}
