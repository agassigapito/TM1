package my.com.tm.portal.leasing.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PortalConfig {

	@Value("${tmportal.soa.system.api.ip}")
	private String systemsApiIPAddr;

	@Value("${tmportal.soa.process.api.ip}")
	private String processApiIPAddr;

	@Value("${tmportal.mock.enabled}")
	private String mockEnabled;

	@Value("${tmportal.dms.document.ip.up}")
	private String dmsServiceDocumentUpIPAddr;

	@Value("${tmportal.dms.document.ip}")
	private String dmsServiceDocumentIPAddr;

	@Value("${tmportal.dms.document.ip.up.port}")
	private int dmsServiceDocumentPortAddr;

	@Value("${tmportal.soa.authentication.username}")
	private String soaUsername;

	@Value("${tmportal.soa.authentication.password}")
	private String soaPassword;

	@Value("${server.port}")
	private int serverPort;

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public String getSoaUsername() {
		return soaUsername;
	}

	public void setSoaUsername(String soaUsername) {
		this.soaUsername = soaUsername;
	}

	public String getSoaPassword() {
		return soaPassword;
	}

	public void setSoaPassword(String soaPassword) {
		this.soaPassword = soaPassword;
	}

	public String getDmsServiceDocumentIPAddr() {
		return dmsServiceDocumentIPAddr;
	}

	public void setDmsServiceDocumentIPAddr(String dmsServiceDocumentIPAddr) {
		this.dmsServiceDocumentIPAddr = dmsServiceDocumentIPAddr;
	}

	public String getSystemsApiIPAddr() {
		return systemsApiIPAddr;
	}

	public void setSystemsApiIPAddr(String systemsApiIPAddr) {
		this.systemsApiIPAddr = systemsApiIPAddr;
	}

	public String getProcessApiIPAddr() {
		return processApiIPAddr;
	}

	public void setProcessApiIPAddr(String processApiIPAddr) {
		this.processApiIPAddr = processApiIPAddr;
	}

	public String getMockEnabled() {
		return mockEnabled;
	}

	public void setMockEnabled(String mockEnabled) {
		this.mockEnabled = mockEnabled;
	}

	public String getDmsServiceDocumentUpIPAddr() {
		return dmsServiceDocumentUpIPAddr;
	}

	public void setDmsServiceDocumentUpIPAddr(String dmsServiceDocumentUpIPAddr) {
		this.dmsServiceDocumentUpIPAddr = dmsServiceDocumentUpIPAddr;
	}

	public Integer getDmsServiceDocumentPortAddr() {
		return dmsServiceDocumentPortAddr;
	}

	public void setDmsServiceDocumentPortAddr(Integer dmsServiceDocumentPortAddr) {
		this.dmsServiceDocumentPortAddr = dmsServiceDocumentPortAddr;
	}

}