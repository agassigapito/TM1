package my.com.tm.portal.leasing.dto.fitout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FitOutDrawingDTO implements Serializable {

	private static final long serialVersionUID = -2216749425123206549L;

	private int fitOutDrawingId;

	private String attachmentFileName;

	@JsonProperty
	private Boolean isCompleted;

	@JsonProperty("fitOutDocuments")
	private List<FitOutDocDTO> fitOutDocList = new ArrayList<>();

	public int getFitOutDrawingId() {
		return fitOutDrawingId;
	}

	public void setFitOutDrawingId(int fitOutDrawingId) {
		this.fitOutDrawingId = fitOutDrawingId;
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

	public List<FitOutDocDTO> getFitOutDocList() {
		return fitOutDocList;
	}

	public void setFitOutDocList(List<FitOutDocDTO> fitOutDocList) {
		this.fitOutDocList = fitOutDocList;
	}

}
