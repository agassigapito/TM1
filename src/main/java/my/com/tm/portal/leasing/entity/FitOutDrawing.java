package my.com.tm.portal.leasing.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import my.com.tm.portal.leasing.auditing.Modifiable;

@Entity
@Table(name = "FIT_OUT_DRAWING")
public class FitOutDrawing extends Modifiable<String> implements Serializable {

	private static final long serialVersionUID = -4329321401024787306L;

	@Id
	@Column(name = "fit_out_drawing_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer fitOutDrawingId;

	@NotNull
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fit_out_id")
	private FitOut fitOut;

	@NotNull
	@Column(name = "attachment_file_name", length = 255)
	private String attachmentFileName;

	@NotNull
	@Column(name = "is_completed")
	private Boolean isCompleted = false;

	@NotNull
	@Column(name = "file_type", length = 5)
	private String fileType;

	public Integer getFitOutDrawingId() {
		return fitOutDrawingId;
	}

	public void setFitOutDrawingId(Integer fitOutDrawingId) {
		this.fitOutDrawingId = fitOutDrawingId;
	}

	public FitOut getFitOut() {
		return fitOut;
	}

	public void setFitOut(FitOut fitOut) {
		this.fitOut = fitOut;
	}

	public String getAttachmentFileName() {
		return attachmentFileName;
	}

	public void setAttachmentFileName(String attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}

	public Boolean getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(Boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

}
