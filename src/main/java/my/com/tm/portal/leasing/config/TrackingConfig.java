package my.com.tm.portal.leasing.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

import my.com.tm.portal.leasing.constant.Constants;

@Aspect
@Configuration
public class TrackingConfig {

	private static final Logger log = LoggerFactory.getLogger(TrackingConfig.class);

	private static final String LOG_FORMAT = "Login ID: {}----------{} {} ----------";

	@Before("execution(* my.com.tm.portal.leasing.service.*.*(..))")
	public void before(JoinPoint joinPoint) {

		log.debug(LOG_FORMAT, SecurityContextHolder.getContext().getAuthentication().getName(), Constants.ENTRY_METHOD,
				joinPoint.getSignature());
	}

	@AfterReturning(value = "execution(* my.com.tm.portal.leasing.service.*.*(..))", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		log.debug("----------{} returned with value {}----------", joinPoint.getSignature(), result);
	}

	@After(value = "execution(* my.com.tm.portal.leasing.service.*.*(..))")
	public void after(JoinPoint joinPoint) {
		log.debug(LOG_FORMAT, SecurityContextHolder.getContext().getAuthentication().getName(), Constants.EXIT_METHOD,
				joinPoint.getSignature());
	}
}
