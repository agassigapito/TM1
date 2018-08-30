package my.com.tm.portal.leasing.utils;

import java.nio.charset.Charset;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import my.com.tm.portal.leasing.constant.Constants;

public final class HttpUtil {

	private HttpUtil() {
	}

	public static HttpHeaders createSOAHeaders(String username, String password) {
		HttpHeaders headers = new HttpHeaders() {
			private static final long serialVersionUID = 4630138222667419530L;

			{
				String auth = username + ":" + password;
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				set(Constants.AUTHORIZATION_HEADER, authHeader);
			}
		};

		headers.set(Constants.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		headers.set(Constants.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		headers.add(Constants.REQUEST_ID, StringUtil.generateUUID());

		return headers;
	}

}
