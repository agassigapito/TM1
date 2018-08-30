package my.com.tm.portal.leasing.service;

import static my.com.tm.portal.leasing.constant.ApiConstants.DMS_FITOUT_DOCUMENT_DOWNLOAD;
import static my.com.tm.portal.leasing.constant.ApiConstants.DMS_FITOUT_DOCUMENT_LIST;
import static my.com.tm.portal.leasing.constant.ApiConstants.DMS_FITOUT_DOCUMENT_UPLOAD;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import my.com.tm.portal.leasing.config.PortalConfig;
import my.com.tm.portal.leasing.dto.DmsDocumentDTO;
import my.com.tm.portal.leasing.dto.DocumentDTO;
import my.com.tm.portal.leasing.dto.DownloadFileDTO;
import my.com.tm.portal.leasing.entity.ApiConfig;
import my.com.tm.portal.leasing.exception.DmsException;
import my.com.tm.portal.leasing.exception.NoEntityFoundException;
import my.com.tm.portal.leasing.model.ResponseStatus;
import my.com.tm.portal.leasing.param.DocumentParam;
import my.com.tm.portal.leasing.repository.ApiConfigRepository;

@Service
public class DmsService {

	private static final Logger log = LoggerFactory.getLogger(DmsService.class);

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private PortalConfig portalConfig;
	@Autowired
	private ApiConfigRepository apiConfigRep;

	private static final String CALLER_ID = "CallerId";
	private static final String CALLER_ID_VAL = "qE2MYVHHawql74rsnRnxTGlw54dAVN7udclALpXWttQ=";
	private static final String LOGIN_ID = "portalftpdev";
	private static final String CREDENTIALS = "Portal@123";
	private static final String PARAMETERS = "parameters";
	private static final String FILE_UPLOAD_STARTED = "Start uploading the file";
	private static final String FILE_UPLOAD_SUCCESSFUL = "File upload successful";
	private static final String LOGGED_ON = "You are now logged on!";
	private static final String LOGGED_ON_FAILED = "Could not login to the server";
	private static final String AUTH_REQUIRED = "Authentication Required";
	private static final String UNKOWN_HOST = "File upload unsuccessful. Host is unknown.";
	private static final String FTP_DISCONNECTED = "FTP Disconnected";
	private static final String OPERATION_FAILED = "Operation failed. Server reply code: {}";
	private static final String NO_DOCUMENT_FOUND = "No matching documents found";

	public ResponseEntity<DownloadFileDTO> downloadFile(Integer documentId, String type) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set(CALLER_ID, CALLER_ID_VAL);
		httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);

		ApiConfig apiConfig = apiConfigRep.findByInterfaceId(DMS_FITOUT_DOCUMENT_DOWNLOAD);
		HttpEntity<String> entity = new HttpEntity<>(PARAMETERS, httpHeaders);
		UriComponents dmsReturn = UriComponentsBuilder
				.fromUriString(portalConfig.getDmsServiceDocumentIPAddr() + apiConfig.getEndpointUrl()).build();
		dmsReturn = dmsReturn.expand(documentId, type);

		log.debug("Calling DMS Service Get Document by Id: {}", dmsReturn);
		return restTemplate.exchange(dmsReturn.toUriString(), HttpMethod.GET, entity, DownloadFileDTO.class);

	}

	public ResponseEntity<DownloadFileDTO> downloadImageByFileName(DocumentParam param) throws NoEntityFoundException {

		DmsDocumentDTO dmsDocuments = this.getDocumentsByDate(param).getBody();
		DocumentDTO documentDTO = dmsDocuments.getDocuments().stream()
				.filter(p -> p.getName().equalsIgnoreCase(param.getAttachmentFileName()))
				.findFirst()
				.orElseThrow(() -> new NoEntityFoundException(NO_DOCUMENT_FOUND));
		return downloadFile(documentDTO.getId(), param.getType());

	}

	public ResponseEntity<DmsDocumentDTO> getDocumentsByDate(DocumentParam param) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set(CALLER_ID, CALLER_ID_VAL);

		ApiConfig apiConfig = apiConfigRep.findByInterfaceId(DMS_FITOUT_DOCUMENT_LIST);
		HttpEntity<String> entity = new HttpEntity<>(PARAMETERS, httpHeaders);
		UriComponents dmsReturn = UriComponentsBuilder
				.fromUriString(portalConfig.getDmsServiceDocumentIPAddr() + apiConfig.getEndpointUrl()).build();

		dmsReturn = dmsReturn.expand(param.getCompanyCode(), param.getCustomer(),
				param.getDocumentDate().substring(0, 4) + param.getDocumentDate().substring(5, 7),
				param.getServiceType(), param.getPageSize(), param.getPageNumber(), param.getType());

		log.debug("Calling DMS get Documents: {}", dmsReturn.toUriString());
		return restTemplate.exchange(dmsReturn.toUriString(), HttpMethod.GET, entity, DmsDocumentDTO.class);
	}

	/**
	 * Uploads file to DMS
	 *
	 * @author agassi.d.h.agapito
	 * @param fileName
	 * @param multipartFile
	 * @return
	 * @throws DmsException
	 */
	public ResponseStatus uploadFile(String fileName, MultipartFile multipartFile) throws DmsException {
		FTPClient ftpClient = new FTPClient();
		String ip = portalConfig.getDmsServiceDocumentUpIPAddr();
		int port = portalConfig.getDmsServiceDocumentPortAddr();
		String url = apiConfigRep.findByInterfaceId(DMS_FITOUT_DOCUMENT_UPLOAD).getEndpointUrl();
		InetAddress netAddress;
		try {
			netAddress = InetAddress.getByName(ip);
		} catch (UnknownHostException e) {
			log.info(e.getMessage(), e);
			throw new DmsException(UNKOWN_HOST);
		}
		try {

			ftpClient.connect(netAddress, port);
			int replyCode = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				log.info(OPERATION_FAILED, replyCode);
				throw new DmsException("Operation failed. Server reply code: " + replyCode);
			}
			boolean success = ftpClient.login(LOGIN_ID, CREDENTIALS);
			if (!success) {
				log.info(LOGGED_ON_FAILED);
				throw new ResourceAccessException(AUTH_REQUIRED);

			} else {
				log.info(LOGGED_ON);
			}
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.setFileTransferMode(FTP.BINARY_FILE_TYPE);
			success = ftpClient.changeWorkingDirectory(url);

			InputStream inputStream = multipartFile.getInputStream();
			log.info(FILE_UPLOAD_STARTED);

			boolean done = ftpClient.storeFile(fileName, inputStream);
			inputStream.close();

			if (done) {
				log.info("{} has been uploaded successfully", fileName);
			}

			return new ResponseStatus(HttpStatus.OK, FILE_UPLOAD_SUCCESSFUL);

		} catch (IOException ex) {
			log.info(ex.getMessage(), ex);
			throw new DmsException("File upload failed: " + ex.getMessage());
		} finally {
			this.disconnect(ftpClient);
		}
	}

	private void disconnect(FTPClient fClient) throws DmsException {
		try {
			if (fClient.isConnected()) {
				fClient.logout();
				fClient.disconnect();
				log.info(FTP_DISCONNECTED);
			}
		} catch (IOException ex) {
			log.info(ex.getMessage(), ex);
			throw new DmsException(ex.getMessage());
		}
	}
}
