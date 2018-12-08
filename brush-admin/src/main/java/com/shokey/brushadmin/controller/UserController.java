package com.shokey.brushadmin.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class UserController {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/index.html")
    public String adminInfo(){
        return "index";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/")
    public String adminInfos(){
        return "index";
    }
}
