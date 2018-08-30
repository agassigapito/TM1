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
@Table(name = "FIT_OUT_WORK")
public class FitOutWork extends FitOutSchedule implements Serializable {

	private static final long serialVersionUID = -6106883441227760977L;

	@Id
	@Column(name = "work_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer workId;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fit_out_id")
	private FitOut fitOut;

	@Column(name = "type", length = 50)
	private String type;

	public Integer getWorkId() {
		return workId;
	}

	public FitOut getFitOut() {
		return fitOut;
	}

	public String getType() {
		return type;
	}

	public void setWorkId(final Integer workId) {
		this.workId = workId;
	}

	public void setFitOut(final FitOut fitOut) {
		this.fitOut = fitOut;
	}

	public void setType(final String type) {
		this.type = type;
	}

}
