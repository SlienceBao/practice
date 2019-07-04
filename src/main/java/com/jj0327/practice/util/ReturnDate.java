package com.jj0327.practice.util;

/**
 * @author jin
 * @Title: ReturnDate
 * @Package com.jj0327.practice.until
 * @Description: 公共返回类
 * @date 2018/9/309:11
 */
public class ReturnDate<T> {
    private int code;
    private String msg;
    private T data;

    @Override
    public String toString() {
        return "Return{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public ReturnDate() {

    }

    public ReturnDate(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
