/**
 * 
 */
package my.com.tm.portal.leasing.dto;

import static my.com.tm.portal.leasing.constant.Constants.SUCCESSFUL_RESPONSE;

import my.com.tm.portal.leasing.model.ResponseStatus;

/**
 * Response wrapper for single return object
 *
 * @author ariel.bisnar
 *
 */
public class SingleWrapperDTO<T> {

	private ResponseStatus responseStatus = SUCCESSFUL_RESPONSE;

	private T responseEntity;

	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(final ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}

	public T getResponseEntity() {
		return responseEntity;
	}

	public void setResponseEntity(final T responseEntity) {
		this.responseEntity = responseEntity;
	}

}
