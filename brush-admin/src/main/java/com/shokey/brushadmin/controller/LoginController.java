package com.shokey.brushadmin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    /** logger */
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/login")
    public String login(Model model){
        return "pages/samples/login";
    }

}
