package com.shokey.brushadmin.controller;

import com.shokey.brushcommon.json.API;
import com.shokey.brushcommon.json.jsonModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HollerController {

    @GetMapping("/takelogin")
    public jsonModel login(){
        return API.error("请使用其他方式请求！");
    }
}
