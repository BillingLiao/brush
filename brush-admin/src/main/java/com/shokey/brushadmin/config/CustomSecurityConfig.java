package com.shokey.brushadmin.config;

import org.springframework.stereotype.Component;

@Component("customSecurityProperties")
public class CustomSecurityConfig {
    // 登录交互类型  JSON:全程使用json进行数据交互   HTML_REDIRECT 页面跳转
    public static final String loginResponseType = "JSON";
    public static final String formLogin_loginPage = "/login";
    public static final String formLogin_loginProcessingUrl = "/takelogin";
    public static final String formLogin_successForwardUrl = "/login";
    public static final String formLogin_usernameParameter = "username";
    public static final String formLogin_passwordParameter = "password";
    public static final String logout_logoutUrl = "/logout";
    public static final String invalidSessionUrl = "/sessionInvalidSessionUrl";
    public static final String expiredSessionUrl = "/sessionExpiredUrl";
    public static final String[] exclusivePaths = {"/css/**", "/js/**", "/fonts/**", "/favicon.ico", "/index", "/needlogin", "/loginPage", "/401", "/404", "/nopermission", "/authentication/form"};
    // cookie名字列表 退出时需要清除
    public static final String[] cookieNames = {"JSESSIONID", "gs", "g_username","g_id"};
}
