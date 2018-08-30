package my.com.tm.portal.leasing.dto.fitout;

import java.io.Serializable;

public class FitOutDocDTO implements Serializable {

	private static final long serialVersionUID = 3243442888841665619L;

	private String displayName;

	private String attachmentFileName;

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
