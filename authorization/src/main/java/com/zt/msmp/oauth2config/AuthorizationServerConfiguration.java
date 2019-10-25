package com.zt.msmp.oauth2config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;


/**
 * @author wangkang
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
        endpoints.accessTokenConverter(jwtAccessTokenConverter);
        endpoints.tokenStore(tokenStore);
        endpoints.userDetailsService(userDetailsService);
        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
        endpoints.exceptionTranslator(loggingExceptionTranslator());
        //refresh_token是否复用，默认true
        endpoints.reuseRefreshTokens(false);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        String finalSecret = "{bcrypt}" + new BCryptPasswordEncoder().encode("123456");
        //配置两个客户端,一个用于password认证一个用于client认证
        clients.inMemory().withClient("first")
                .resourceIds("zt")
                .authorizedGrantTypes("authorization_code", "refresh_token")
                .scopes("select")
                .authorities("oauth2")
                .secret(finalSecret)
                .redirectUris("https://www.baidu.com")
                .accessTokenValiditySeconds(60 * 60 * 2)
                .refreshTokenValiditySeconds(60 * 60 * 24 * 3)
                .and()
                .withClient("second")
                .resourceIds("zt")
                .authorizedGrantTypes("authorization_code", "password", "refresh_token")
                .scopes("select")
                .authorities("oauth2")
                .secret(finalSecret)
                .accessTokenValiditySeconds(60 * 60 * 2)
                .refreshTokenValiditySeconds(60 * 60 * 24 * 3)
                .and()
                .withClient("third")
                .resourceIds("order")
                .authorizedGrantTypes("client_credentials")
                .scopes("select")
                .authorities("oauth2")
                .secret(finalSecret)
                .accessTokenValiditySeconds(60 * 60 * 2);
        //.refreshTokenValiditySeconds(60 * 60 * 24 * 3);
        //  clients.withClientDetails(clientDetails());
    }

  /* @Bean
    public ClientDetailsService clientDetails() {
        InMemoryClientDetailsService inMemoryClientDetailsService = new InMemoryClientDetailsService();
        return inMemoryClientDetailsService;
    }*/


    /**
     * @return JwtTokenStore
     */


    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        //允许表单认证
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("permitAll()").allowFormAuthenticationForClients();
    }

    @Bean
    public WebResponseExceptionTranslator loggingExceptionTranslator() {
        return new DefaultWebResponseExceptionTranslator() {
            @Override
            public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
                e.printStackTrace();
                ResponseEntity<OAuth2Exception> responseEntity = super.translate(e);
                HttpHeaders headers = new HttpHeaders();
                headers.setAll(responseEntity.getHeaders().toSingleValueMap());
                OAuth2Exception excBody = responseEntity.getBody();
                return new ResponseEntity<>(excBody, headers, responseEntity.getStatusCode());
            }
        };
    }
}
