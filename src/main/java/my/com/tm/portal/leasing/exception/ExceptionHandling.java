package my.com.tm.portal.leasing.exception;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;

import my.com.tm.portal.leasing.constant.ErrorMessage;
import my.com.tm.portal.leasing.model.ResponseStatus;

@ControllerAdvice
public class ExceptionHandling {

	private static final Logger log = LoggerFactory.getLogger(ExceptionHandling.class);
	private static final String ERROR_TEMPLATE = "ErrorID: {} ErrorValue: {}";

	private static String getErrorResponse(String errorId, String errorMessage) {
		return "ErrorID: " + errorId + " " + errorMessage;

	}

	@ExceptionHandler(IOException.class)
	public ResponseEntity<ResponseStatus> handleIOException(IOException e) {
		String errorId = UUID.randomUUID().toString();
		log.error(ERROR_TEMPLATE, errorId, e.getMessage());
		log.error(e.getMessage(), IOException.class.getName());
		return new ResponseEntity<>(new ResponseStatus(getErrorResponse(errorId, ErrorMessage.JSON_FILE_IS_EMPTY)),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ HttpClientErrorException.class, HttpServerErrorException.class })
	public ResponseEntity<ResponseStatus> handleHttpException(RestClientResponseException e) {
		String errorId = UUID.randomUUID().toString();
		log.error(ERROR_TEMPLATE, errorId, e.getResponseBodyAsString());
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(new ResponseStatus(getErrorResponse(errorId, e.getResponseBodyAsString())),
				HttpStatus.CONFLICT);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseStatus> handleGenericException(Exception e) {
		String errorId = UUID.randomUUID().toString();
		log.error(ERROR_TEMPLATE, errorId, e.getMessage());
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(new ResponseStatus(getErrorResponse(errorId, e.getMessage())),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NoEntityFoundException.class)
	public ResponseEntity<ResponseStatus> handleEmptyFileException(NoEntityFoundException e) {
		String errorId = UUID.randomUUID().toString();
		log.error(ERROR_TEMPLATE, errorId, e.getMessage());
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(new ResponseStatus(getErrorResponse(errorId, e.getMessage())),
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ResourceAccessException.class)
	public ResponseEntity<ResponseStatus> handleResourceAccessException(ResourceAccessException e) {
		String errorId = UUID.randomUUID().toString();
		log.error(ERROR_TEMPLATE, errorId, e.getMessage());
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(new ResponseStatus(getErrorResponse(errorId, e.getMessage())),
				HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(Wso2RequestException.class)
	public ResponseEntity<ResponseStatus> handleWso2RequestException(Exception e) {
		String errorId = UUID.randomUUID().toString();
		log.error(ERROR_TEMPLATE, errorId, e.getMessage());
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(new ResponseStatus(getErrorResponse(errorId, e.getMessage())),
				HttpStatus.CONFLICT);
	}

	@ExceptionHandler(InvalidParamException.class)
	public ResponseEntity<ResponseStatus> handleInvalidParamException(Exception e) {
		String errorId = UUID.randomUUID().toString();
		log.error(ERROR_TEMPLATE, errorId, e.getMessage());
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(new ResponseStatus(getErrorResponse(errorId, e.getMessage())),
				HttpStatus.BAD_REQUEST);
	}
}
