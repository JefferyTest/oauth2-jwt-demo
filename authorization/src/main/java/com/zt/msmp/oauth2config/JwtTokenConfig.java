package com.zt.msmp.oauth2config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangkang
 */
@Configuration
 public class JwtTokenConfig{

        @Bean
        public TokenStore jwtTokenStore(){
            return new InMemoryTokenStore();
           // return new JwtTokenStore(accessTokenConverter());
        }

        /**
         * token生成处理：指定签名
         */
        @Bean
        public JwtAccessTokenConverter accessTokenConverter() {
            JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter() {
                /***
                 * 重写增强token方法,用于自定义一些token返回的信息
                 */
                @Override
                public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
                    //  String userName = authentication.getUserAuthentication().getName();
                    // 与登录时候放进去的UserDetail实现类一直查看
                    //  User user = (User) authentication.getUserAuthentication().getPrincipal();
                    Map<String, Object> additionalInformation = new HashMap<>();
                    additionalInformation.put("url", "http://www.zhengtongit.com/");
                    ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
                    return super.enhance(accessToken, authentication);
                }

            };
            // 测试用,资源服务使用相同的字符达到一个对称加密的效果,生产时候使用RSA非对称加密方式
            accessTokenConverter.setSigningKey("123");
            // 非对称加密
      /*  KeyStoreKeyFactory keyStoreKeyFactory =
                new KeyStoreKeyFactory(new ClassPathResource("ztcloud.jks"), "zhengtongit".toCharArray());
        accessTokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair("ztcloud"));*/
            return accessTokenConverter;
        }
}
