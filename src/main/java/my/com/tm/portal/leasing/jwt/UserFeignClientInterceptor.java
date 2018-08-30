package my.com.tm.portal.leasing.jwt;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import my.com.tm.portal.leasing.constant.Constants;

@Component
public class UserFeignClientInterceptor implements RequestInterceptor {

	   @Override
	    public void apply(RequestTemplate template) {
	        SecurityContext securityContext = SecurityContextHolder.getContext();
	        Authentication authentication = securityContext.getAuthentication();

	        if (authentication != null) {
	        	 StringBuilder token = new StringBuilder(Jwts.builder()
	                     .setSubject(authentication.getPrincipal().toString())
	                     .setExpiration(new Date(System.currentTimeMillis() + Constants.EXPIRATION_TIME))
	                     .signWith(SignatureAlgorithm.HS512, Constants.SECRET.getBytes())
	                     .compact());

	        	template.header(Constants.AUTHORIZATION_HEADER, String.format("%s %s", Constants.TOKEN_PREFIX, token.toString()));
	        }
	    }

}
