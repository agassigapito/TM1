package my.com.tm.portal.leasing;

import java.util.Collections;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import my.com.tm.portal.leasing.logging.LoggingRestTemplate;

@EnableAsync
@Primary
@EnableAutoConfiguration
@RestController
@EnableFeignClients
@EnableDiscoveryClient
@EnableCaching
@SpringBootApplication
@EnableEurekaClient
public class TmLeasingPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(TmLeasingPortalApplication.class, args);
	}

	@Bean
	public RestTemplate rest(RestTemplateBuilder builder) {
		RestTemplate restTemplate = builder.build();
		restTemplate.setInterceptors(Collections.singletonList(new LoggingRestTemplate()));
		return restTemplate;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
