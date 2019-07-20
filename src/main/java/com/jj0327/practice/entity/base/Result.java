package com.jj0327.practice.entity.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 统一返回
 */
@Getter
@Setter
@ToString
public class Result<T> implements Serializable {

    /**
     * 成功状态码
     */
    public static final int SUCCESS_CODE = 0;

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 响应消息
     */
    private String msg;
    /**
     * 存放的数据
     */
    private T data;


    public Result() {
    }

    private Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> success() {
        return new Result<>(SUCCESS_CODE, "success");
    }

    public static <T> Result<T> success(String msg) {
        return new Result<>(SUCCESS_CODE, msg);
    }

    public static <T> Result<T> success(String msg, T data) {
        return new Result<>(SUCCESS_CODE, msg, data);
    }

    public static <T> Result<T> success(BaseEnum baseEnum) {
        return new Result<>(baseEnum.getCode(), baseEnum.getMsg());
    }

    public static <T> Result<T> success(BaseEnum baseEnum, T data) {
        return new Result<>(baseEnum.getCode(), baseEnum.getMsg(), data);
    }

    public static <T> Result<T> fail(int code, String msg, T data) {
        return new Result<>(code, msg, data);
    }

    public static <T> Result<T> fail(int code, String msg) {
        return new Result<>(code, msg);
    }

    public static <T> Result<T> fail(BaseEnum baseEnum) {
        return new Result<>(baseEnum.getCode(), baseEnum.getMsg());
    }

}
