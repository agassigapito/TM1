package my.com.tm.portal.leasing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Document {

	@JsonProperty("Binary")
	private String binary;

	public String getBinary() {
		return binary;
	}

	public void setBinary(String binary) {
		this.binary = binary;
	}

}
