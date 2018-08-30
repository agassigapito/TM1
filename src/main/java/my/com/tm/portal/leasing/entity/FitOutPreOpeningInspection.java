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
@Table(name = "FIT_OUT_PRE_OPENING_INSPECTION")
public class FitOutPreOpeningInspection extends FitOutSchedule implements Serializable {

	private static final long serialVersionUID = 178848210761129550L;

	@Id
	@Column(name = "pre_opening_inspection_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer preOpeningInspectionId;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fit_out_id")
	private FitOut fitOut;

	@Column(name = "is_latest")
	private boolean isLatest;

	public Integer getPreOpeningInspectionId() {
		return preOpeningInspectionId;
	}

	public FitOut getFitOut() {
		return fitOut;
	}

	public boolean isLatest() {
		return isLatest;
	}

	public void setPreOpeningInspectionId(final Integer preOpeningInspectionId) {
		this.preOpeningInspectionId = preOpeningInspectionId;
	}

	public void setFitOut(final FitOut fitOut) {
		this.fitOut = fitOut;
	}

	public void setLatest(final boolean isLatest) {
		this.isLatest = isLatest;
	}

}
