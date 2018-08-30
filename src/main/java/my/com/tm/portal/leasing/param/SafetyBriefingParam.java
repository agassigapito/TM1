/**
 * 
 */
package my.com.tm.portal.leasing.param;

/**
 * @author ariel.bisnar
 *
 */
public class SafetyBriefingParam {

	private Integer fitOutId;
	private String proposedDateTime;
	private String tenantRemarks;
	private String appointmentDateTime;
	private String fitOutTeamRemarks;
	private String lastModifiedBy;

	public Integer getFitOutId() {
		return fitOutId;
	}
	public String getProposedDateTime() {
		return proposedDateTime;
	}
	public String getTenantRemarks() {
		return tenantRemarks;
	}
	public String getAppointmentDateTime() {
		return appointmentDateTime;
	}
	public String getFitOutTeamRemarks() {
		return fitOutTeamRemarks;
	}
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}
	public void setFitOutId(final Integer fitOutId) {
		this.fitOutId = fitOutId;
	}
	public void setProposedDateTime(final String proposedDateTime) {
		this.proposedDateTime = proposedDateTime;
	}
	public void setTenantRemarks(final String tenantRemarks) {
		this.tenantRemarks = tenantRemarks;
	}
	public void setAppointmentDateTime(final String appointmentDateTime) {
		this.appointmentDateTime = appointmentDateTime;
	}
	public void setFitOutTeamRemarks(final String fitOutTeamRemarks) {
		this.fitOutTeamRemarks = fitOutTeamRemarks;
	}
	public void setLastModifiedBy(final String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

}
