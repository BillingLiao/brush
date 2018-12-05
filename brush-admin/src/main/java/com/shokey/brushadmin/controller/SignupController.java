package com.shokey.brushadmin.controller;

import com.shokey.brushadmin.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignupController {

    @Autowired
    private UserServer userServer;

    @RequestMapping("/signup")
    public String Signup(Model model){
        return "signup";
    }

    @PostMapping("/tikesignup")
    public String Signup(@RequestParam String phone, @RequestParam String pass, Model model) throws Exception {
        if (userServer.Signup(phone,pass))
            return "login";
        else
            return "signup";
    }
}
