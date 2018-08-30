package my.com.tm.portal.leasing.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DocumentDTO {

	@JsonProperty("Id")
	private Integer id;

	@JsonProperty("Name")
	private String name;

	@JsonProperty("CompanyCode")
	private String companyCode;

	@JsonProperty("CustomerNo")
	private String customerNo;

	@JsonProperty("DocumentNo")
	private String documentNo;

	@JsonProperty("DocumentDate")
	private String documentDate;

	@JsonProperty("DocumentType")
	private String documentType;

	@JsonProperty("ContentType")
	private String contentType;

	@JsonProperty("Binary")
	private String binary;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	public String getDocumentDate() {
		return documentDate;
	}

	public void setDocumentDate(String documentDate) {
		this.documentDate = documentDate;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getBinary() {
		return binary;
	}

	public void setBinary(String binary) {
		this.binary = binary;
	}

}
