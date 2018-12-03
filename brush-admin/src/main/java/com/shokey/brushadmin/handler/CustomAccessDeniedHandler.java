package com.shokey.brushadmin.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shokey.brushadmin.config.CustomSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 访问拒绝
 * <p>
 * 一般是对已经认证过  但授权不足的情况进行处理
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException, ServletException {
        if (CustomSecurityConfig.loginResponseType.equals("JSON")) {

            String src = "权限不足，请联系管理员：" + exception.getLocalizedMessage() + exception.getMessage()+"时间："+exception.getCause();
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(src));

        }
    }
}