package com.shokey.brushadmin.config;

import org.springframework.stereotype.Component;

@Component("customSecurityProperties")
public class CustomSecurityConfig {
    public static final String formLogin_loginPage = "/login";
    public static final String formLogin_loginProcessingUrl = "/takelogin";
    public static final String formLogin_successForwardUrl = "/";
    public static final String formLogin_usernameParameter = "username";
    public static final String formLogin_passwordParameter = "password";
    public static final String logout_logoutUrl = "/logout";
    public static final String invalidSessionUrl = "/sessionInvalidSessionUrl";
    public static final String expiredSessionUrl = "/sessionExpiredUrl";
    public static final String[] exclusivePaths = {"/fonts/**","/images/**","/js/**","/css/**","/vendors/**","/static/**", "/favicon.ico", "/tikesignup", "/login","/takelogin", "/401", "/404","/404","/500", "/signup", "/register.html"};
    // cookie名字列表 退出时需要清除
    public static final String[] cookieNames = {"JSESSIONID", "gs", "g_username","g_id"};
}
