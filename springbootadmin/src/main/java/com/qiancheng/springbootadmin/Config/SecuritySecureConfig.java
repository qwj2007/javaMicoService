package com.qiancheng.springbootadmin.Config;


import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * 作者：qiwj
 * 时间：2020/7/11
 * Sring Boot Admin添加认证配置文件
 */

@Configuration
public class SecuritySecureConfig extends WebSecurityConfigurerAdapter {
    private final String adminContextPath;

    public SecuritySecureConfig(AdminServerProperties adminServerProperties) {
        this.adminContextPath = adminServerProperties.getContextPath();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
// @formatter:off
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter( "redirectTo" );

        http.authorizeRequests()
                // 只开启eureka注册账密校验
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/instances").permitAll()
                //静态文件允许访问
                .antMatchers( adminContextPath + "/assets/**" ).permitAll()
                //登录页面允许访问
                .antMatchers( adminContextPath + "/login" ).permitAll()
                //其他所有请求需要登录
                .anyRequest().authenticated()
                .and()
                //登录页面配置，用于替换security默认页面
                .formLogin().loginPage( adminContextPath + "/login" ).successHandler( successHandler ).and()
                //登出页面配置，用于替换security默认页面
                .logout().logoutUrl( adminContextPath + "/logout" ).and()
                .httpBasic().and()
                .csrf().disable();
        // @formatter:on

    }


}//*/
