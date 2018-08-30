package my.com.tm.portal.leasing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;
import my.com.tm.portal.leasing.jwt.UserFeignClientInterceptor;

@Configuration
public class Wso2ClientConfig {

     @Bean
     public RequestInterceptor getUserFeignClientInterceptor() {
         return new UserFeignClientInterceptor();
     }

}