package com.supos.eco.vo;

public class RestResult<T> {
    private Long code = 0L;
    private String msg = "Success";
    private T data;

    public RestResult() {

    }

    public static <T> RestResult<T> success(T data) {
        return new RestResult<>(0L,"success",data);
    }
    public static RestResult success() {
        return new RestResult<>(0L,"success",null);
    }
    public RestResult(Long code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
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
