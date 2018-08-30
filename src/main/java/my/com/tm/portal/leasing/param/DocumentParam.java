package my.com.tm.portal.leasing.param;

import java.io.Serializable;

import io.swagger.annotations.ApiParam;

public class DocumentParam implements Serializable {

	private static final long serialVersionUID = 2420467206977896176L;

	@ApiParam(value = "YYYYMM", required = true)
	private String documentDate;
	@ApiParam(value = "ALL, if not specified", required = true)
	private String companyCode;
	@ApiParam(required = true)
	private String customer;
	@ApiParam(required = true)
	private String pageSize;
	@ApiParam(required = true)
	private String pageNumber;
	@ApiParam(value = "", required = true)
	private String type;
	@ApiParam(value = "WO, CP, UR, R", required = true)
	private String serviceType;
	@ApiParam(value = "FIT_LD__1223647283__20181010_admin LD.pdf", required = true)
	private String attachmentFileName;

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getDocumentDate() {
		return documentDate;
	}

	public void setDocumentDate(String documentDate) {
		this.documentDate = documentDate;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getAttachmentFileName() {
		return attachmentFileName;
	}

	public void setAttachmentFileName(String attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}

}
