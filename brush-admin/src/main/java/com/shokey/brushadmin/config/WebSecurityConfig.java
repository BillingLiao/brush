package com.shokey.brushadmin.config;

import com.shokey.brushadmin.black.Black2b;
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
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

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
                .csrf().disable()
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler)
                .and()
                //权限处理
                .authorizeRequests()
                .antMatchers("/tikesignup").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/signup","/register.html").permitAll()
                .antMatchers("/fonts/**","/images/**","/js/**","/css/**","/vendors/**","/static/**").permitAll()//静态资源
                .antMatchers("/404","/500").permitAll()//系统页面
                .anyRequest().authenticated() //其他所有资源都需要认证，登陆后访问
                //登陆处理
                .and()
                .formLogin()
                .loginPage(CustomSecurityConfig.formLogin_loginPage)//指定登录页是”/login”
                .loginProcessingUrl(CustomSecurityConfig.formLogin_loginProcessingUrl)//这里的配置已经没用了
                .successForwardUrl(CustomSecurityConfig.formLogin_successForwardUrl)//这里的配置已经没用了
                .usernameParameter(CustomSecurityConfig.formLogin_usernameParameter)//表单name
                .passwordParameter(CustomSecurityConfig.formLogin_passwordParameter)//表单name
                .permitAll(true)
                .failureHandler(customAuthenticationFailureHandler)//自定义登陆失败处理器
                .successHandler(customAuthenticationSuccessHandler)//自定义登陆成功处理器
//                .failureUrl("/login?loginFailed=1")// 登录失败后跳转的路径,为了给客户端提示
//                .defaultSuccessUrl("/user")// 登录成功后默认跳转的路径;
//                .successHandler(loginSuccessHandler()) //登录成功后可使用loginSuccessHandler()存储用户信息，可选。
                //退出登陆
                .and()
                .logout()
                .logoutUrl(CustomSecurityConfig.logout_logoutUrl)//退出url
                .permitAll()
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies(CustomSecurityConfig.cookieNames)
                .logoutSuccessHandler(customLogoutSuccessHandler)//退出处理器
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
//                .logoutSuccessUrl("/login?logout=1") //退出登录
//                .permitAll()
//                .invalidateHttpSession(true)
                .and()
//                .and()
//                .rememberMe()//登录后记住用户，下次自动登录,数据库中必须存在名为persistent_logins的表
//                .tokenValiditySeconds(1209600);
        //session处理
                .sessionManagement()
                .invalidSessionStrategy(invalidSessionStrategy) //session无效处理策略
                .invalidSessionUrl(CustomSecurityConfig.invalidSessionUrl)
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

    @Override
    public void configure(WebSecurity web) throws Exception {
        /*
         * 在springboot中忽略静态文件路径，直接写静态文件的文件夹 springboot默认有静态文件的放置路径，如果应用spring
         * security，配置忽略路径 不应该从springboot默认的静态文件开始
         * 如：在本项目中，所有的js和css都放在static下，如果配置忽略路径，则不能以static开始
         * 配置成web.ignoring().antMatchers("/static/*");这样是不起作用的
         */

        web.ignoring().antMatchers("/themes/**", "/script/**");

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

}
