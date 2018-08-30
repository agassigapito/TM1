package my.com.tm.portal.leasing.exception;

public class DmsException extends Exception{

	private static final long serialVersionUID = -3250854013667135143L;

	public DmsException(String message) {
		super(message);
	}

	public DmsException(String message, Throwable cause) {
		super(message, cause);
	}
}
