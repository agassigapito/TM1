package my.com.tm.portal.leasing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HeaderDTO {

	@JsonProperty("Success")
	private String success;

	@JsonProperty("ErrorCode")
	private String errorCode;

	@JsonProperty("ErrorMessage")
	private String errorMessage;

	@JsonProperty("ItemCount")
	private String itemcount;

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getItemcount() {
		return itemcount;
	}

	public void setItemcount(String itemcount) {
		this.itemcount = itemcount;
	}

}
