package com.shokey.brushadmin.config;

import com.shokey.brushadmin.black.Black2b;
import com.shokey.brushadmin.filter.MyUsernamePasswordAuthenticationFilter;
import com.shokey.brushadmin.handler.CustomAccessDeniedHandler;
import com.shokey.brushadmin.handler.CustomAuthenticationFailureHandler;
import com.shokey.brushadmin.handler.CustomAuthenticationSuccessHandler;
import com.shokey.brushadmin.handler.CustomLogoutSuccessHandler;
import com.shokey.brushadmin.server.UserDetailServer;
import com.shokey.brushadmin.session.CustomExpiredSessionStrategy;
import com.shokey.brushadmin.session.CustomInvalidSessionStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled =true)//开启注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /** logger */
    private static final Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;

    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;



    @Override/*拦截器*/
    protected void configure(HttpSecurity http) throws Exception {
        http

                .addFilterBefore(myUsernamePasswordAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class)//自定义登录验证器
                .csrf().disable()
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler)
                .and()//权限处理
                .authorizeRequests()//
                .antMatchers(CustomSecurityConfig.exclusivePaths).permitAll()//无需权限
                .anyRequest().authenticated() //其他所有资源都需要认证，登陆后访问
                .and()//登陆处理
                .formLogin()//
                .loginPage(CustomSecurityConfig.formLogin_loginPage)//指定登录页是”/login”
                .loginProcessingUrl(CustomSecurityConfig.formLogin_loginProcessingUrl)//现在有用了
                .successForwardUrl(CustomSecurityConfig.formLogin_successForwardUrl)//现在有用了
                .usernameParameter(CustomSecurityConfig.formLogin_usernameParameter)//表单name
                .passwordParameter(CustomSecurityConfig.formLogin_passwordParameter)//表单name
                .and()//退出登陆
                .logout()//
                .logoutUrl(CustomSecurityConfig.logout_logoutUrl)//退出url
                .permitAll()//
                .clearAuthentication(true)//
                .invalidateHttpSession(true)//
                .deleteCookies(CustomSecurityConfig.cookieNames)//
                .logoutSuccessHandler(customLogoutSuccessHandler)//退出处理器
                .and()//session处理
                .sessionManagement()//
                .invalidSessionStrategy(invalidSessionStrategy) //session无效处理策略
                .invalidSessionUrl(CustomSecurityConfig.invalidSessionUrl)//
                .maximumSessions(1)  //同一用户最大session数
                .maxSessionsPreventsLogin(false) //达到最大数禁止登录（预防并发登录）
                .expiredSessionStrategy(sessionInformationExpiredStrategy) //session过期处理策略
                .expiredUrl(CustomSecurityConfig.expiredSessionUrl);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return Black2b.encode(64,charSequence.toString());
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(Black2b.encode(64,charSequence.toString()));
            }
        });
    }

    /**
     * 我也不知道这个到底有没有用
     * 管他妈先写在这在说
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers( "/script/**");
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailServer();
    }

    @Bean(name="myAuthenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @ConditionalOnMissingBean(InvalidSessionStrategy.class)
    public InvalidSessionStrategy invalidSessionStrategy() {
        return new CustomInvalidSessionStrategy(CustomSecurityConfig.invalidSessionUrl);
    }

    @Bean
    @ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
    public SessionInformationExpiredStrategy sessionInformationExpiredStrategy() {
        return new CustomExpiredSessionStrategy(CustomSecurityConfig.invalidSessionUrl);
    }

    private MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter() throws Exception {
        MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter = new MyUsernamePasswordAuthenticationFilter();
        myUsernamePasswordAuthenticationFilter.setAuthenticationManager(this.authenticationManager());//必要
        myUsernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);//处理失败
        myUsernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);//处理成功
        return myUsernamePasswordAuthenticationFilter;
    }

}
