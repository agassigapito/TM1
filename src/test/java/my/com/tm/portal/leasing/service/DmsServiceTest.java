package my.com.tm.portal.leasing.service;

import static my.com.tm.portal.leasing.constant.ApiConstants.DMS_FITOUT_DOCUMENT_DOWNLOAD;
import static my.com.tm.portal.leasing.constant.ApiConstants.DMS_FITOUT_DOCUMENT_LIST;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import my.com.tm.portal.leasing.config.PortalConfig;
import my.com.tm.portal.leasing.dto.DmsDocumentDTO;
import my.com.tm.portal.leasing.dto.Document;
import my.com.tm.portal.leasing.dto.DocumentDTO;
import my.com.tm.portal.leasing.dto.DownloadFileDTO;
import my.com.tm.portal.leasing.entity.ApiConfig;
import my.com.tm.portal.leasing.exception.NoEntityFoundException;
import my.com.tm.portal.leasing.param.DocumentParam;
import my.com.tm.portal.leasing.repository.ApiConfigRepository;

/**
 *
 * @author agassi.d.h.agapito
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class DmsServiceTest {

	@InjectMocks
	private DmsService dmsService;
	@Mock
	private PortalConfig portalConfig;
	@Mock
	private ApiConfigRepository apiConfigRepository;
	@Mock
	private RestTemplate restTemplate;

	private DownloadFileDTO downloadFileDTO;
	private Document document;
	private ResponseEntity<DownloadFileDTO> downloadResponseOk;
	private ResponseEntity<DownloadFileDTO> downloadResponseFailed;
	private ResponseEntity<DmsDocumentDTO> downloadDmsResponseOk;
	private ResponseEntity<DmsDocumentDTO> downloadDmsResponseFailed;
	private String sampleBinary;
	private ApiConfig apiConfigDownloadList;
	private ApiConfig apiConfigDownloadById;
	private ApiConfig apiConfigUpload;
	private DocumentParam documentParam;
	private String fileName;
	private DmsDocumentDTO dmsDocumentDTO;
	private DocumentDTO documentDTO;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		sampleBinary = "sample binary";
		fileName = "Jasmine is cool!";
		document = new Document();
		document.setBinary(sampleBinary);

		downloadFileDTO = new DownloadFileDTO();
		downloadFileDTO.setDocument(document);

		downloadResponseOk = new ResponseEntity<>(downloadFileDTO, HttpStatus.OK);
		downloadResponseFailed = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

		downloadDmsResponseFailed = new ResponseEntity<>(new DmsDocumentDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
		apiConfigDownloadList = new ApiConfig();
		apiConfigDownloadList.setEndpointUrl(
				"/GetDocuments.svc/Company/{companyCode}/Customer/{customer}/YYYYMM/{YYYYMM}/ServiceType/{serviceType}/PageSize/{pageSize}/PageIndex/{pageNumber}/Documents?DocumentType={type}");
		apiConfigDownloadById = new ApiConfig();
		apiConfigDownloadById.setEndpointUrl("/GetDocuments.svc/Document/{documentId}/Documents?DocumentType={type}");
		apiConfigUpload = new ApiConfig();
		apiConfigUpload.setEndpointUrl("/DEV/Portal/OutboundPortal/SharePoint/RL/FITOUT/Out");

		documentParam = new DocumentParam();
		documentParam.setAttachmentFileName(fileName);
		documentParam.setCompanyCode("1160");
		documentParam.setCustomer("001000100");
		documentParam.setDocumentDate("1994-04-08");
		documentParam.setPageNumber("1");
		documentParam.setPageSize("100");
		documentParam.setServiceType("FIT");
		documentParam.setType("F");

		documentDTO = new DocumentDTO();
		documentDTO.setName(fileName);

		dmsDocumentDTO = new DmsDocumentDTO();
		dmsDocumentDTO.getDocuments().add(documentDTO);

		downloadDmsResponseOk = new ResponseEntity<>(dmsDocumentDTO, HttpStatus.OK);
	}

	@After
	public void tearDown() {
		sampleBinary = null;
		document = null;
		downloadFileDTO = null;
		downloadResponseOk = null;
		apiConfigDownloadList = null;
		apiConfigDownloadById = null;
		apiConfigUpload = null;
		downloadResponseFailed = null;
		fileName = null;
		dmsDocumentDTO = null;
		documentDTO = null;
		downloadDmsResponseFailed = null;
	}

	@Test
	public void downloadFile_NoDocumentFound_ShouldReturnHttpInternalServerError() {
		when(apiConfigRepository.findByInterfaceId(anyString())).thenReturn(apiConfigDownloadById);
		when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(DownloadFileDTO.class)))
				.thenReturn(downloadResponseFailed);
		ResponseEntity<DownloadFileDTO> response = dmsService.downloadFile(1, "pdf");
		assertEquals(downloadResponseFailed, response);
	}

	@Test
	public void downloadFile_OneDocumentFound_ShouldReturnHttpOk() {
		when(apiConfigRepository.findByInterfaceId(anyString())).thenReturn(apiConfigDownloadById);
		when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(DownloadFileDTO.class)))
				.thenReturn(downloadResponseOk);
		ResponseEntity<DownloadFileDTO> response = dmsService.downloadFile(1, "pdf");
		assertEquals(downloadResponseOk, response);
	}

	@Test(expected = NoEntityFoundException.class)
	public void downloadImageByFileName_NoDocumentFound_ShouldReturnHttpInternalServerError()
			throws NoEntityFoundException {
		when(apiConfigRepository.findByInterfaceId(anyString())).thenReturn(apiConfigDownloadList);
		when(portalConfig.getDmsServiceDocumentIPAddr()).thenReturn("10.2.20.45");
		when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(DmsDocumentDTO.class)))
				.thenReturn(downloadDmsResponseFailed);
		ResponseEntity<DownloadFileDTO> response = dmsService.downloadImageByFileName(documentParam);
		assertEquals(null, response);
	}

	@Test
	public void downloadImageByFileName_OneDocumentFound_ShouldReturnOneDocument() throws NoEntityFoundException {
		when(apiConfigRepository.findByInterfaceId(eq(DMS_FITOUT_DOCUMENT_LIST))).thenReturn(apiConfigDownloadList);
		when(apiConfigRepository.findByInterfaceId(eq(DMS_FITOUT_DOCUMENT_DOWNLOAD))).thenReturn(apiConfigDownloadById);
		when(portalConfig.getDmsServiceDocumentIPAddr()).thenReturn("10.2.20.45");
		when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(DownloadFileDTO.class)))
				.thenReturn(downloadResponseOk);
		when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(DmsDocumentDTO.class)))
				.thenReturn(downloadDmsResponseOk);
		ResponseEntity<DownloadFileDTO> response = dmsService.downloadImageByFileName(documentParam);
		assertEquals(downloadResponseOk, response);
	}
}
