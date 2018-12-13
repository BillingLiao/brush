package com.shokey.brushadmin.handler;

import com.shokey.brushcommon.exception.RRException;
import com.shokey.brushcommon.json.API;
import com.shokey.brushcommon.json.jsonModel;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class ExceptionHandle {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public jsonModel handle(Exception e) throws Exception {
        if (e instanceof RRException) {//自定义异常类，抛出
            throw e;
        }else if (e instanceof RuntimeException){//这里是登录处理相关异常，抛出去自定义处理
            throw e;
        }
        return API.error();
    }
}
