package com.zt.demo.config;


import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


/**
 * @author wangkang
 */
@Configuration
@EnableWebSecurity
@Order(2)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("wang")
                .password(new BCryptPasswordEncoder().encode("123"))
                .roles("admin")
                .and()
                .withUser("kang")
                .password(new BCryptPasswordEncoder().encode("123"))
                .roles("user");
    }*/

    /**
     * 设置不拦截规则
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/js/**",
                "/css/**",
                "/images/**",
                "/druid/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/oauth/**", "/login")
                .permitAll()
                .and().formLogin()
                .loginPage("/login/login.html")
                .loginProcessingUrl("/formLogin")
                .successHandler((httpServletRequest, httpServletResponse, authentication) -> {
                            httpServletResponse.setContentType("application/json;charset=utf-8");
                            Object principal = authentication.getPrincipal();
                            PrintWriter out = httpServletResponse.getWriter();
                            out.write(JSON.toJSONString(principal));
                            out.flush();
                            out.close();
                        }
                ).failureHandler((httpServletRequest, httpServletResponse, e) -> {
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter out = httpServletResponse.getWriter();
                    Map<String, Object> map = new HashMap<>(4);
                    map.put("status", 401);
                    map.put("error_description", "用户名密码错误");
                    out.write(JSON.toJSONString(map));
                    out.flush();
                    out.close();
                }
        )
                /*  .and()
                  .sessionManagement()
                  .sessionCreationPolicy(SessionCreationPolicy.STATELESS)*/
                .and().csrf().disable();

    }
}
