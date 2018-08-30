package my.com.tm.portal.leasing.dto;

import static my.com.tm.portal.leasing.constant.Constants.SUCCESSFUL_RESPONSE;

import java.util.ArrayList;
import java.util.List;

import my.com.tm.portal.leasing.model.ResponseStatus;

public class ListWrapperDTO<T> {

	private ResponseStatus responseStatus = SUCCESSFUL_RESPONSE;

	private List<T> list = new ArrayList<>();

	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
