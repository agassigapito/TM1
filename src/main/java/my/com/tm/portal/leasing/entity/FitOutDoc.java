package my.com.tm.portal.leasing.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "FIT_OUT_DOC")
public class FitOutDoc implements Serializable {

	private static final long serialVersionUID = -865857869144390885L;

	@Id
	@Column(name = "fit_out_doc_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer fitOutDocId;

	@NotNull
	@Column(name = "building_name", length = 60)
	private String buildingName;

	@NotNull
	@Column(name = "display_name", length = 60)
	private String displayName;

	@NotNull
	@Column(name = "attachment_file_name", length = 255)
	private String attachmentFileName;

	public Integer getFitOutDocId() {
		return fitOutDocId;
	}

	public void setFitOutDocId(Integer fitOutDocId) {
		this.fitOutDocId = fitOutDocId;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getAttachmentFileName() {
		return attachmentFileName;
	}

	public void setAttachmentFileName(String attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}

}
