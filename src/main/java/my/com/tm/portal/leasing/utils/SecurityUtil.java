package my.com.tm.portal.leasing.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import my.com.tm.portal.leasing.constant.Constants;

public final class SecurityUtil {

	public static String getCurrentAuditor() {
  	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return (authentication == null || !authentication.isAuthenticated()) ? Constants.SYSTEM : authentication.getName();
	}
}
