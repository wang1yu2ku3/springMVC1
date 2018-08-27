package com.wyl.test.dto;

import java.io.Serializable;

/**
 * @Auther: wangyulin
 * @Date: 2018/8/19 14:46
 * @Description:
 */
public class BaseResponse<T> implements Serializable {

    private boolean success;
    private Integer code;
    private String message;
    private T data;

    public BaseResponse(T data) {
        this.data = data;
        this.success = true;
        this.code = 0;
    }

    public BaseResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.success = false;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
