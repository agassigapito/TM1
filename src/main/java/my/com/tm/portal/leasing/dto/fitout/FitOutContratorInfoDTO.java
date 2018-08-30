package my.com.tm.portal.leasing.dto.fitout;

import java.io.Serializable;

import my.com.tm.portal.leasing.entity.FitOut;

public class FitOutContratorInfoDTO implements Serializable {
	private static final long serialVersionUID = 3243442888841665619L;

	private Integer fitOutContractorInfoId;
	
	private String refNo;

	private String fitOutId;

	private String companyName;

	private String contactPerson;

	private String mobileNo;

	private String telephoneNo;
	
	private String emailAddress;

	/**
	 * @return the fitOutContractorInfoId
	 */
	public Integer getFitOutContractorInfoId() {
		return fitOutContractorInfoId;
	}

	/**
	 * @param fitOutContractorInfoId the fitOutContractorInfoId to set
	 */
	public void setFitOutContractorInfoId(Integer fitOutContractorInfoId) {
		this.fitOutContractorInfoId = fitOutContractorInfoId;
	}

	/**
	 * @return the refNo
	 */
	public String getRefNo() {
		return refNo;
	}

	/**
	 * @param refNo the refNo to set
	 */
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	/**
	 * @return the fitOutId
	 */
	public String getFitOutId() {
		return fitOutId;
	}

	/**
	 * @param fitOutId the fitOutId to set
	 */
	public void setFitOutId(String fitOutId) {
		this.fitOutId = fitOutId;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the contactPerson
	 */
	public String getContactPerson() {
		return contactPerson;
	}

	/**
	 * @param contactPerson the contactPerson to set
	 */
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	/**
	 * @return the mobileNo
	 */
	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * @param mobileNo the mobileNo to set
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * @return the telephoneNo
	 */
	public String getTelephoneNo() {
		return telephoneNo;
	}

	/**
	 * @param telephoneNo the telephoneNo to set
	 */
	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

}
