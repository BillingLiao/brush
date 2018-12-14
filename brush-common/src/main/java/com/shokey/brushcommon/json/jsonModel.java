package com.shokey.brushcommon.json;

/**
 * 统一json模型
 */
public class jsonModel<T> {

    //状态码
    private Integer code;

    //信息
    private String msg;

    //data。。。。。。。
    private T data;

    public jsonModel(Integer code,String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public jsonModel(){}

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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

    @Override
    public String toString() {
        return "{\"code\":"+this.code+",\"msg\":\""+this.msg+"\",\"data\":\""+this.data+"\"}";
    }
}
