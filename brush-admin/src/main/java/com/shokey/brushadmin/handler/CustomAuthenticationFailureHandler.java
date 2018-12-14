package com.shokey.brushadmin.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shokey.brushadmin.config.CustomSecurityConfig;
import com.shokey.brushcommon.exception.MyUsernameNotFoundException;
import com.shokey.brushcommon.json.API;
import com.shokey.brushcommon.tool.HTTPUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Autowired
    private ObjectMapper objectMapper;

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
            }else if (exception instanceof DisabledException) {
                msg.append("账户不允许登录");
            }else if (exception instanceof LockedException) {
                msg.append("账号被锁定");
            }else {
                msg.append(exception.getLocalizedMessage()).append(exception.getMessage());
            }

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(API.login_no(msg.toString())));

        } else {
            request.getRequestDispatcher(this.defaultFailureUrl).forward(request, response);
        }

    }

    public void setDefaultFailureUrl(String defaultFailureUrl) {
        Assert.isTrue(UrlUtils.isValidRedirectUrl(defaultFailureUrl), "'" + defaultFailureUrl + "' is not a valid redirect URL");
        this.defaultFailureUrl = defaultFailureUrl;
    }
}