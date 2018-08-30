package my.com.tm.portal.leasing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import my.com.tm.portal.leasing.constant.Constants;
import my.com.tm.portal.leasing.jwt.CORSFilter;
import my.com.tm.portal.leasing.jwt.JWTAuthorizationFilter;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String[] COOKIES = { "JSESSIONID", "XSRF-TOKEN", "Authorization" };
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/swagger-resources/**",
				"/configuration/security", "/swagger-ui.html", "/webjars/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();
		http.cors().and().authorizeRequests()
				.antMatchers(HttpMethod.POST, Constants.SWAGGER).permitAll()
				.antMatchers("/asset-mgt-service/default").permitAll()
				.anyRequest().authenticated()
				.and()
				.logout().deleteCookies(COOKIES)
				.logoutUrl("/logout").logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.ACCEPTED))
				.and()
				.addFilter(new JWTAuthorizationFilter(authenticationManager()))
				.addFilterBefore(new CORSFilter(), UsernamePasswordAuthenticationFilter.class)
				// this disables session creation on Spring Security
				 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}


}
