package com.shokey.brushadmin.handler;

import com.brush.admin.config.CustomSecurityConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
    @Autowired
    private ObjectMapper objectMaper;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        if (CustomSecurityConfig.loginResponseType.equals("JSON")) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMaper.writeValueAsString("退出登陆成功"));
        } else {
            super.handle(request, response, authentication);
        }
    }
}