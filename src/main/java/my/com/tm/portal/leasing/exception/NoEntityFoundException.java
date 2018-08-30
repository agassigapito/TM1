package my.com.tm.portal.leasing.exception;

public class NoEntityFoundException extends Exception {

	private static final long serialVersionUID = -6405962056284279998L;

	public NoEntityFoundException(String message) {
		super(message);
	}

	public NoEntityFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
