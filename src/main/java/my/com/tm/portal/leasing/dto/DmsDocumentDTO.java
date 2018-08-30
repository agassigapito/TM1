package my.com.tm.portal.leasing.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DmsDocumentDTO {

	@JsonProperty("Header")
	private HeaderDTO header;

	@JsonProperty("Documents")
	private List<DocumentDTO> documentDTOs = new ArrayList<>();

	public HeaderDTO getHeader() {
		return header;
	}

	public void setHeader(HeaderDTO header) {
		this.header = header;
	}

	public List<DocumentDTO> getDocuments() {
		return documentDTOs;
	}

	public void setDocuments(List<DocumentDTO> documentDTOs) {
		this.documentDTOs = documentDTOs;
	}

}
