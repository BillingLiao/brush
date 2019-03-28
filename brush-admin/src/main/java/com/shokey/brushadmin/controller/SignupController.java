package com.shokey.brushadmin.controller;

import com.shokey.brushadmin.server.UserServer;
import com.shokey.brushcommon.tool.HTTPUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SignupController {

    @Autowired
    private UserServer userServer;

    @RequestMapping("/register.html")
    public String Signup(Model model){
        return "pages/samples/register";//注册页面地址可以多配
    }

    /**
     * 只能使用页面注册方式，不允许ajax注册
     * @param phone
     * @param pass
     * @param model
     * @return
     * @throws Exception
     */
    @PostMapping("/tikesignup")
    public String Signup(HttpServletRequest request, @RequestParam("phone") String phone, @RequestParam("pass") String pass, Model model) throws Exception {
        if (!HTTPUtils.isAjaxRequest(request)) {
            if (userServer.Signup(phone, pass))
                return "pages/samples/login";
            else
                return "pages/samples/register";
        }else {
            return "pages/samples/register";
        }
    }
}
