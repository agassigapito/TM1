package my.com.tm.portal.leasing.model;


import java.io.Serializable;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseStatus implements Serializable {

	private static final long serialVersionUID = 1647976541492718020L;

	@JsonProperty("Status")
    private String status;

    @JsonProperty("StatusCode")
    private String statusCode;

    @JsonProperty("StatusMessage")
    private String statusMessage;

    public String getStatus() {
        return status;
    }

    public ResponseStatus() {}
    public ResponseStatus(String statusMessage) {
        this.statusMessage = statusMessage;
    }
    public ResponseStatus(HttpStatus httpStatus, String statusMessage) {
        this.status = httpStatus.getReasonPhrase();
        this.statusCode = httpStatus.value() +"";
        this.statusMessage = statusMessage;
    }
    public ResponseStatus(String status, String statusCode, String statusMessage) {
        this.status = status;
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
