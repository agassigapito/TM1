package my.com.tm.portal.leasing.param;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.SwaggerDefinition;

public class FitOutDrawingParam implements Serializable {

	private static final long serialVersionUID = 7118480276443617735L;

	private Integer fitOutId;

	private String bpNumber;

	private Boolean isCompleted;

	@ApiModelProperty(allowableValues = "1")
	public Integer getFitOutId() {
		return fitOutId;
	}

	public void setFitOutId(Integer fitOutId) {
		this.fitOutId = fitOutId;
	}
	@ApiModelProperty(allowableValues = "0100000100")
	public String getBpNumber() {
		return bpNumber;
	}

	public void setBpNumber(String bpNumber) {
		this.bpNumber = bpNumber;
	}

	public Boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(Boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

}
