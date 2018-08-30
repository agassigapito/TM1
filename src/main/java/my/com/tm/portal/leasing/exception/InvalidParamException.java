package my.com.tm.portal.leasing.exception;

public class InvalidParamException extends Exception {

	private static final long serialVersionUID = 4008577777622358803L;

	public InvalidParamException(String message) {
		super(message);
	}

	public InvalidParamException(String message, Throwable cause) {
		super(message, cause);
	}
}
