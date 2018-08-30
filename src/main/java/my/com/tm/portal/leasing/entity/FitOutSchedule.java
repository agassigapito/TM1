/**
 * 
 */
package my.com.tm.portal.leasing.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.format.annotation.DateTimeFormat;

import my.com.tm.portal.leasing.auditing.Modifiable;

/**
 * @author ariel.bisnar
 *
 */
@MappedSuperclass
public abstract class FitOutSchedule extends Modifiable<String> {

	@DateTimeFormat(iso = DateTimeFormat.ISO.NONE)
	@Column(name = "proposed_date_time")
	private LocalDateTime proposedDateTime;

	@Column(name = "tenant_remarks", length = 300)
	private String tenantRemarks;

	@DateTimeFormat(iso = DateTimeFormat.ISO.NONE)
	@Column(name = "appointment_date_time")
	private LocalDateTime appointmentDateTime;

	@Column(name = "fit_out_team_remarks", length = 300)
	private String fitOutTeamRemarks;

	public LocalDateTime getProposedDateTime() {
		return proposedDateTime;
	}

	public String getTenantRemarks() {
		return tenantRemarks;
	}

	public LocalDateTime getAppointmentDateTime() {
		return appointmentDateTime;
	}

	public String getFitOutTeamRemarks() {
		return fitOutTeamRemarks;
	}

	public void setProposedDateTime(final LocalDateTime proposedDateTime) {
		this.proposedDateTime = proposedDateTime;
	}

	public void setTenantRemarks(final String tenantRemarks) {
		this.tenantRemarks = tenantRemarks;
	}

	public void setAppointmentDateTime(final LocalDateTime appointmentDateTime) {
		this.appointmentDateTime = appointmentDateTime;
	}

	public void setFitOutTeamRemarks(final String fitOutTeamRemarks) {
		this.fitOutTeamRemarks = fitOutTeamRemarks;
	}

}
