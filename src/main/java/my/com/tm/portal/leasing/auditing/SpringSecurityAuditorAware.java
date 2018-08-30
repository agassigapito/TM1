package my.com.tm.portal.leasing.auditing;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import my.com.tm.portal.leasing.constant.Constants;


public class SpringSecurityAuditorAware implements AuditorAware<String> {

	@Override
	public String getCurrentAuditor() {
  	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return (authentication == null || !authentication.isAuthenticated()) ? Constants.SYSTEM : authentication.getName();
	}

}
