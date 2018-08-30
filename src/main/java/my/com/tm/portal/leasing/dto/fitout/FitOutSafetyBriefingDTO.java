/**
 * 
 */
package my.com.tm.portal.leasing.dto.fitout;

/**
 * @author ariel.bisnar
 *
 */
public class FitOutSafetyBriefingDTO {

	private String proposedDateTime;
	private String tenantRemarks;
	private String appointmentDateTime;
	private String fitOutTeamRemarks;
	private boolean isLatest;

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
	public boolean isLatest() {
		return isLatest;
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
	public void setLatest(final boolean isLatest) {
		this.isLatest = isLatest;
	}
	
}
