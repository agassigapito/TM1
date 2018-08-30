package my.com.tm.portal.leasing.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "FIT_OUT_CONTRACTOR_INFO")
public class FitOutContractorInfo implements Serializable {

	private static final long serialVersionUID = 2985112470493834241L;

	@Id
	@Column(name = "fit_out_contractor_info_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer fitOutContractorInfoId;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fit_out_id")
	private FitOut fitOut;
	
	@Column(name = "company_name")
	private String companyName;
	
	@Column(name = "contact_person")
	private String contactPerson;
	
	@Column(name = "mobile_no")
	private String mobileNo;
	
	@Column(name = "telephone_no")
	private String telephoneNo;
	
	@Column(name = "email_address")
	private String emailAddress;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "created_datetime")
	private String createdDatetime;
	
	@Column(name = "modify_by")
	private String modifyBy;
	
	@Column(name = "modify_datetime")
	private String modifyDatetime;

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
	 * @return the fitOut
	 */
	public FitOut getFitOut() {
		return fitOut;
	}

	/**
	 * @param fitOut the fitOut to set
	 */
	public void setFitOut(FitOut fitOut) {
		this.fitOut = fitOut;
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

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdDatetime
	 */
	public String getCreatedDatetime() {
		return createdDatetime;
	}

	/**
	 * @param createdDatetime the createdDatetime to set
	 */
	public void setCreatedDatetime(String createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	/**
	 * @return the modifyBy
	 */
	public String getModifyBy() {
		return modifyBy;
	}

	/**
	 * @param modifyBy the modifyBy to set
	 */
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	/**
	 * @return the modifyDatetime
	 */
	public String getModifyDatetime() {
		return modifyDatetime;
	}

	/**
	 * @param modifyDatetime the modifyDatetime to set
	 */
	public void setModifyDatetime(String modifyDatetime) {
		this.modifyDatetime = modifyDatetime;
	}

}
