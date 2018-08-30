package my.com.tm.portal.leasing.exception;

public class Wso2RequestException extends Exception{
	private static final long serialVersionUID = -2652491120464327264L;

	public Wso2RequestException(String message) {
		super(message);
	}

	public Wso2RequestException(String message, Throwable cause) {
		super(message, cause);
	}
}
