package com.shokey.brushcommon.json;

import com.shokey.brushcommon.tool.CodeConstant;

/**
 * 接口数据方法
 */
public class API {

    //登录成功
    public static jsonModel<Object> login_ok(Object object){
        jsonModel<Object> jsonModel = new jsonModel<>();
        jsonModel.setCode(CodeConstant.SUCCESS);
        jsonModel.setMsg("登录成功");
        jsonModel.setData(object);
        return jsonModel;
    }

    //登录失败
    public static jsonModel login_no(String msg){
        jsonModel jsonModel = new jsonModel<>();
        jsonModel.setCode(CodeConstant.MAT_ERR);
        jsonModel.setMsg(msg);
        return jsonModel;
    }

    //成功结果
    public static jsonModel<Object> success(Object object){
        jsonModel<Object> jsonModel = new jsonModel<>();
        jsonModel.setCode(CodeConstant.SUCCESS);
        jsonModel.setMsg("成功");
        jsonModel.setData(object);
        return jsonModel;
    }
    //成功不带信息
    public static jsonModel success(){
        jsonModel<Object> jsonModel = new jsonModel<>();
        jsonModel.setCode(CodeConstant.SUCCESS);
        jsonModel.setMsg("成功");
        return jsonModel;
    }
    //成功自定义信息
    public static jsonModel success(String msg){
        jsonModel<Object> jsonModel = new jsonModel<>();
        jsonModel.setCode(CodeConstant.SUCCESS);
        jsonModel.setMsg(msg);
        return jsonModel;
    }

    //通用错误
    public static jsonModel error(){
        jsonModel jsonModel = new jsonModel();
        jsonModel.setCode(CodeConstant.ERROR);
        jsonModel.setMsg("错误！");
        return jsonModel;
    }

    //登录过期,自定义错误信息
    public static jsonModel error(String msg){
        jsonModel jsonModel = new jsonModel();
        jsonModel.setCode(CodeConstant.ERR_AUTH);
        jsonModel.setMsg(msg);
        return jsonModel;
    }

    //权限不足
    public static jsonModel insufficient(){
        jsonModel jsonModel = new jsonModel();
        jsonModel.setCode(CodeConstant.NO_AUTH);
        jsonModel.setMsg("权限不足");
        return jsonModel;
    }
}
