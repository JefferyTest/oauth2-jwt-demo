package com.zt.msmp.oauth2config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;


/**
 * @author wangkang
 * @date 2019-03-05 13:36
 */
@Configuration
@EnableResourceServer
@Order(6)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId("order").stateless(true);
        resources.tokenServices(defaultTokenServices());
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        //TODO 此处配置需要拦截的url
        http.csrf().disable().authorizeRequests()
                .antMatchers("/order/**").authenticated();
    }

    /**
     * Token转换器必须与认证服务一致
     */
    private JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        // 测试用,授权服务使用相同的字符达到一个对称加密的效果,生产时候使用RSA非对称加密方式
        accessTokenConverter.setSigningKey("123");
        return accessTokenConverter;
    }

    @Bean
    public ResourceServerTokenServices defaultTokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenEnhancer(accessTokenConverter());
        defaultTokenServices.setTokenStore(tokenStore);
        return defaultTokenServices;
    }
}
