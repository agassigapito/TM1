/**
 * 
 */
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

/**
 * @author ariel.bisnar
 *
 */
@Entity
@Table(name = "FIT_OUT_SAFETY_BRIEFING")
public class FitOutSafetyBriefing extends FitOutSchedule implements Serializable {

	private static final long serialVersionUID = -7111547781758540027L;

	@Id
	@Column(name = "safety_briefing_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer safetyBriefingId;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fit_out_id")
	private FitOut fitOut;

	@Column(name = "is_latest")
	private boolean isLatest;

	public Integer getSafetyBriefingId() {
		return safetyBriefingId;
	}

	public FitOut getFitOut() {
		return fitOut;
	}

	public boolean isLatest() {
		return isLatest;
	}

	public void setSafetyBriefingId(final Integer safetyBriefingId) {
		this.safetyBriefingId = safetyBriefingId;
	}

	public void setFitOut(final FitOut fitOut) {
		this.fitOut = fitOut;
	}

	public void setLatest(final boolean isLatest) {
		this.isLatest = isLatest;
	}

}
