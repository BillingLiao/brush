package com.shokey.brushadmin.controller;

import com.shokey.brushadmin.server.UserServer;
import com.shokey.brushcommon.json.API;
import com.shokey.brushcommon.json.jsonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HollerController {

    @Autowired
    private UserServer userServer;

    @GetMapping("/takelogin")
    public jsonModel login(){
        return API.error("请使用其他方式请求！");
    }

    @GetMapping("/tikesignup")
    public jsonModel sign_up(){
        return API.error("请使用其他方式请求！");
    }

    @RequestMapping()
    public jsonModel Activation(@RequestParam String code){
        return userServer.Activation(code);
    }

    @RequestMapping("/test")
    public jsonModel test(){
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();//获取当前用户所以信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return API.success(authentication);
    }
}
