package com.shokey.brushadmin.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shokey.brushcommon.json.API;
import com.shokey.brushcommon.tool.HTTPUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private String defaultFailureUrl;

    private final ObjectMapper objectMapper;

    @Autowired
    public CustomAuthenticationFailureHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {


        if (HTTPUtils.isAjaxRequest(request)) {

            StringBuilder msg = new StringBuilder();

            if (exception instanceof AccountExpiredException) {
                msg.append("账户过期");
            }else if (exception instanceof BadCredentialsException) {
                msg.append("密码错误");
            }else if (exception instanceof CredentialsExpiredException) {
                msg.append("证书过期");
            }else if (exception instanceof DisabledException) {//这里在我找到更好的方法时没用
                msg.append("账户未激活");
            }else if (exception instanceof LockedException) {
                msg.append("账号被锁定");
            }else {
                msg.append(exception.getLocalizedMessage()).append(exception.getMessage());
            }

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(API.login_no(msg.toString())));

        } else {
            if (exception instanceof AccountExpiredException)
                request.getRequestDispatcher(this.defaultFailureUrl).forward(request, response);
            else if (exception instanceof BadCredentialsException)
                request.getRequestDispatcher(this.defaultFailureUrl).forward(request, response);
            else if (exception instanceof CredentialsExpiredException)
                request.getRequestDispatcher(this.defaultFailureUrl).forward(request, response);
            else if (exception instanceof DisabledException)
                request.getRequestDispatcher(this.defaultFailureUrl).forward(request, response);
            else if (exception instanceof LockedException)
                request.getRequestDispatcher(this.defaultFailureUrl).forward(request, response);
            else
                request.getRequestDispatcher(this.defaultFailureUrl).forward(request, response);
        }

    }

    public void setDefaultFailureUrl(String defaultFailureUrl) {
        Assert.isTrue(UrlUtils.isValidRedirectUrl(defaultFailureUrl), "'" + defaultFailureUrl + "' is not a valid redirect URL");
        this.defaultFailureUrl = defaultFailureUrl;
    }
}