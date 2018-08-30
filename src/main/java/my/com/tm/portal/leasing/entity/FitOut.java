package my.com.tm.portal.leasing.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FIT_OUT")
public class FitOut implements Serializable {

	private static final long serialVersionUID = 2985112470493834241L;

	@Id
	@Column(name = "fit_out_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer fitOutId;
	
	@Column(name = "ref_no")
	private String refNo;

	@Column(name = "building_code")
	private String buildingCode;

	@Column(name = "level")
	private String level;

	@Column(name = "demised_premises")
	private String demisedPremises;

	@Column(name = "trade_name")
	private String tradeName;

	@Column(name = "company_code")
	private String companyCode;

	@Column(name = "overall_status")
	private String overallStatus;

	@Column(name = "vacant_possession")
	private String vacantPossession;

	@Column(name = "fit_out_start")
	private String fitOutStart;

	@Column(name = "fit_out_end")
	private String fitOutEnd;

	@Column(name = "fit_out_duration")
	private String fitOutDuration;

	@Column(name = "loo_date")
	private String looDate;

	@Column(name = "area_size_lot")
	private String areaSizeLot;

	@Column(name = "area_size_alfresco")
	private String areaSizeAlfresco;

	@Column(name = "permitted_use")
	private String permittedUse;

	@Column(name = "trading_hours")
	private String tradingHours;

	@Column(name = "payment_status")
	private String paymentStatus;

	@Column(name = "principal_property_insurance")
	private String principalPropertyInsurance;

	@Column(name = "liability_insurance_limit")
	private String liabilityInsuranceLimit;

	@Column(name = "risk_insurance")
	private String riskInsurance;

	@Column(name = "contact_person")
	private String contactPerson;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "email")
	private String email;

	@Column(name = "address")
	private String address;

	@Column(name = "tca_reference_no")
	private String tcaReferenceNo;

	@Column(name = "tca_document")
	private String tcaDocument;

	@Column(name = "fit_out_type")
	private String fitOutType;

	@Column(name = "special_condition")
	private String specialCondition;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_datetime")
	private String createdDatetime;

	@Column(name = "last_modified_datetime")
	private String lastModifiedDatetime;

	@Column(name = "last_modified_by")
	private String lastModifiedBy;

	@OneToMany(mappedBy = "fitOut", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FitOutContractorInfo> fitOutContractorInfo = new ArrayList<>();
	
	@OneToMany(mappedBy = "fitOut", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FitOutSafetyBriefing> fitOutSafetyBriefingList = new ArrayList<>();

	@OneToMany(mappedBy = "fitOut", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FitOutWork> fitOutWorkList = new ArrayList<>();

	@OneToMany(mappedBy = "fitOut", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FitOutPreOpeningInspection> fitOutPreOpeningInspectionList = new ArrayList<>();

	@OneToOne(mappedBy = "fitOut", cascade = CascadeType.ALL, orphanRemoval = true)
	private FitOutDrawing fitOutDrawing;

	public Integer getFitOutId() {
		return fitOutId;
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
	 * @return the buildingCode
	 */
	public String getBuildingCode() {
		return buildingCode;
	}

	/**
	 * @param buildingCode the buildingCode to set
	 */
	public void setBuildingCode(String buildingCode) {
		this.buildingCode = buildingCode;
	}

	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * @return the demisedPremises
	 */
	public String getDemisedPremises() {
		return demisedPremises;
	}

	/**
	 * @param demisedPremises the demisedPremises to set
	 */
	public void setDemisedPremises(String demisedPremises) {
		this.demisedPremises = demisedPremises;
	}

	/**
	 * @return the tradeName
	 */
	public String getTradeName() {
		return tradeName;
	}

	/**
	 * @param tradeName the tradeName to set
	 */
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	/**
	 * @return the companyCode
	 */
	public String getCompanyCode() {
		return companyCode;
	}

	/**
	 * @param companyCode the companyCode to set
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	/**
	 * @return the overallStatus
	 */
	public String getOverallStatus() {
		return overallStatus;
	}

	/**
	 * @param overallStatus the overallStatus to set
	 */
	public void setOverallStatus(String overallStatus) {
		this.overallStatus = overallStatus;
	}

	/**
	 * @return the vacantPossession
	 */
	public String getVacantPossession() {
		return vacantPossession;
	}

	/**
	 * @param vacantPossession the vacantPossession to set
	 */
	public void setVacantPossession(String vacantPossession) {
		this.vacantPossession = vacantPossession;
	}

	/**
	 * @return the fitOutStart
	 */
	public String getFitOutStart() {
		return fitOutStart;
	}

	/**
	 * @param fitOutStart the fitOutStart to set
	 */
	public void setFitOutStart(String fitOutStart) {
		this.fitOutStart = fitOutStart;
	}

	/**
	 * @return the fitOutEnd
	 */
	public String getFitOutEnd() {
		return fitOutEnd;
	}

	/**
	 * @param fitOutEnd the fitOutEnd to set
	 */
	public void setFitOutEnd(String fitOutEnd) {
		this.fitOutEnd = fitOutEnd;
	}

	/**
	 * @return the fitOutDuration
	 */
	public String getFitOutDuration() {
		return fitOutDuration;
	}

	/**
	 * @param fitOutDuration the fitOutDuration to set
	 */
	public void setFitOutDuration(String fitOutDuration) {
		this.fitOutDuration = fitOutDuration;
	}

	/**
	 * @return the looDate
	 */
	public String getLooDate() {
		return looDate;
	}

	/**
	 * @param looDate the looDate to set
	 */
	public void setLooDate(String looDate) {
		this.looDate = looDate;
	}

	/**
	 * @return the areaSizeLot
	 */
	public String getAreaSizeLot() {
		return areaSizeLot;
	}

	/**
	 * @param areaSizeLot the areaSizeLot to set
	 */
	public void setAreaSizeLot(String areaSizeLot) {
		this.areaSizeLot = areaSizeLot;
	}

	/**
	 * @return the areaSizeAlfresco
	 */
	public String getAreaSizeAlfresco() {
		return areaSizeAlfresco;
	}

	/**
	 * @param areaSizeAlfresco the areaSizeAlfresco to set
	 */
	public void setAreaSizeAlfresco(String areaSizeAlfresco) {
		this.areaSizeAlfresco = areaSizeAlfresco;
	}

	/**
	 * @return the permittedUse
	 */
	public String getPermittedUse() {
		return permittedUse;
	}

	/**
	 * @param permittedUse the permittedUse to set
	 */
	public void setPermittedUse(String permittedUse) {
		this.permittedUse = permittedUse;
	}

	/**
	 * @return the tradingHours
	 */
	public String getTradingHours() {
		return tradingHours;
	}

	/**
	 * @param tradingHours the tradingHours to set
	 */
	public void setTradingHours(String tradingHours) {
		this.tradingHours = tradingHours;
	}

	/**
	 * @return the paymentStatus
	 */
	public String getPaymentStatus() {
		return paymentStatus;
	}

	/**
	 * @param paymentStatus the paymentStatus to set
	 */
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	/**
	 * @return the principalPropertyInsurance
	 */
	public String getPrincipalPropertyInsurance() {
		return principalPropertyInsurance;
	}

	/**
	 * @param principalPropertyInsurance the principalPropertyInsurance to set
	 */
	public void setPrincipalPropertyInsurance(String principalPropertyInsurance) {
		this.principalPropertyInsurance = principalPropertyInsurance;
	}

	/**
	 * @return the liabilityInsuranceLimit
	 */
	public String getLiabilityInsuranceLimit() {
		return liabilityInsuranceLimit;
	}

	/**
	 * @param liabilityInsuranceLimit the liabilityInsuranceLimit to set
	 */
	public void setLiabilityInsuranceLimit(String liabilityInsuranceLimit) {
		this.liabilityInsuranceLimit = liabilityInsuranceLimit;
	}

	/**
	 * @return the riskInsurance
	 */
	public String getRiskInsurance() {
		return riskInsurance;
	}

	/**
	 * @param riskInsurance the riskInsurance to set
	 */
	public void setRiskInsurance(String riskInsurance) {
		this.riskInsurance = riskInsurance;
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
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the tcaReferenceNo
	 */
	public String getTcaReferenceNo() {
		return tcaReferenceNo;
	}

	/**
	 * @param tcaReferenceNo the tcaReferenceNo to set
	 */
	public void setTcaReferenceNo(String tcaReferenceNo) {
		this.tcaReferenceNo = tcaReferenceNo;
	}

	/**
	 * @return the tcaDocument
	 */
	public String getTcaDocument() {
		return tcaDocument;
	}

	/**
	 * @param tcaDocument the tcaDocument to set
	 */
	public void setTcaDocument(String tcaDocument) {
		this.tcaDocument = tcaDocument;
	}

	/**
	 * @return the fitOutType
	 */
	public String getFitOutType() {
		return fitOutType;
	}

	/**
	 * @param fitOutType the fitOutType to set
	 */
	public void setFitOutType(String fitOutType) {
		this.fitOutType = fitOutType;
	}

	/**
	 * @return the specialCondition
	 */
	public String getSpecialCondition() {
		return specialCondition;
	}

	/**
	 * @param specialCondition the specialCondition to set
	 */
	public void setSpecialCondition(String specialCondition) {
		this.specialCondition = specialCondition;
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
	 * @return the lastModifiedDatetime
	 */
	public String getLastModifiedDatetime() {
		return lastModifiedDatetime;
	}

	/**
	 * @param lastModifiedDatetime the lastModifiedDatetime to set
	 */
	public void setLastModifiedDatetime(String lastModifiedDatetime) {
		this.lastModifiedDatetime = lastModifiedDatetime;
	}

	/**
	 * @return the lastModifiedBy
	 */
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	/**
	 * @param lastModifiedBy the lastModifiedBy to set
	 */
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	/**
	 * @return the fitOutContractorInfo
	 */
	public List<FitOutContractorInfo> getFitOutContractorInfo() {
		return fitOutContractorInfo;
	}

	/**
	 * @param fitOutContractorInfo the fitOutContractorInfo to set
	 */
	public void setFitOutContractorInfo(List<FitOutContractorInfo> fitOutContractorInfo) {
		this.fitOutContractorInfo = fitOutContractorInfo;
	}

	public List<FitOutSafetyBriefing> getFitOutSafetyBriefingList() {
		return fitOutSafetyBriefingList;
	}

	public void setFitOutId(final Integer fitOutId) {
		this.fitOutId = fitOutId;
	}

	public void setFitOutSafetyBriefingList(final List<FitOutSafetyBriefing> fitOutSafetyBriefingList) {
		this.fitOutSafetyBriefingList = fitOutSafetyBriefingList;
	}

	public List<FitOutWork> getFitOutWorkList() {
		return fitOutWorkList;
	}

	public void setFitOutWorkList(final List<FitOutWork> fitOutWorkList) {
		this.fitOutWorkList = fitOutWorkList;
	}

	public List<FitOutPreOpeningInspection> getFitOutPreOpeningInspectionList() {
		return fitOutPreOpeningInspectionList;
	}

	public void setFitOutPreOpeningInspectionList(final List<FitOutPreOpeningInspection> fitOutPreOpeningInspectionList) {
		this.fitOutPreOpeningInspectionList = fitOutPreOpeningInspectionList;
	}

	public FitOutDrawing getFitOutDrawing() {
		return fitOutDrawing;
	}

	public void setFitOutDrawing(FitOutDrawing fitOutDrawing) {
		this.fitOutDrawing = fitOutDrawing;
	}

}
