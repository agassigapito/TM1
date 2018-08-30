package my.com.tm.portal.leasing.service.fitout;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import my.com.tm.portal.leasing.constant.Constants;
import my.com.tm.portal.leasing.dto.fitout.FitOutDocDTO;
import my.com.tm.portal.leasing.dto.fitout.FitOutDrawingDTO;
import my.com.tm.portal.leasing.entity.FitOut;
import my.com.tm.portal.leasing.entity.FitOutDoc;
import my.com.tm.portal.leasing.entity.FitOutDrawing;
import my.com.tm.portal.leasing.exception.DmsException;
import my.com.tm.portal.leasing.exception.NoEntityFoundException;
import my.com.tm.portal.leasing.model.ResponseStatus;
import my.com.tm.portal.leasing.repository.FitOutDocRepository;
import my.com.tm.portal.leasing.repository.FitOutDrawingRepository;
import my.com.tm.portal.leasing.repository.FitOutRepository;
import my.com.tm.portal.leasing.service.DmsService;
import my.com.tm.portal.leasing.service.fitout.FitOutDrawingService;

/**
 *
 * @author agassi.d.h.agapito
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class FitOutDrawingServiceTest {

	@InjectMocks
	private FitOutDrawingService fitOutDrawingService;
	@Mock
	private FitOutDrawingRepository fitOutDrawingRepository;
	@Mock
	private DmsService dmsService;
	@Mock
	private FitOutDocRepository fitOutDocRepository;
	@Mock
	private FitOutRepository fitOutRepository;

	private FitOutDrawingDTO fitOutDrawingDTO;
	private FitOut fitOutWithDrawing;
	private FitOut fitOutWithoutDrawing;
	private List<FitOutDoc> fitOutDocList;
	private FitOutDoc fitOutDoc;
	private String buildingName;
	private int fitOutDrawingId;
	private int fitOutId;
	private int fitOutDocId;
	private FitOutDrawing fitOutDrawing;
	private String fitOutDrawingFileName;
	private FitOutDocDTO fitOutDocDTO;
	private String fitOutDocFileName;
	private String fitOutDocDisplayName;
	private String bpNumber;
	private MockMultipartFile drawingFile;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		fitOutDrawingId = 1;
		fitOutId = 1;
		fitOutDocId = 1;

		buildingName = "Starling";
		fitOutDrawingFileName = "FIT_LD__0100000100__" + LocalDate.now().toString() + "_DMS LD.pdf";
		fitOutDocFileName = "Starling Foodies’ Nest Fit Out Document_sample_file_name";
		fitOutDocDisplayName = "Starling Foodies’ Nest Fit Out Document";

		fitOutDrawing = new FitOutDrawing();
		fitOutDrawing.setAttachmentFileName(fitOutDrawingFileName);
		fitOutDrawing.setFileType(Constants.FILE_TYPE_PDF);
		fitOutDrawing.setFitOutDrawingId(fitOutDrawingId);
		fitOutDrawing.setIsCompleted(true);
		fitOutDrawing.setLastModifiedBy("Aga");
		fitOutDrawing.setLastModifiedDateTime(new Date());

		fitOutWithDrawing = new FitOut();
		fitOutWithDrawing.setFitOutDrawing(fitOutDrawing);

		fitOutWithoutDrawing = new FitOut();

		fitOutDoc = new FitOutDoc();
		fitOutDoc.setAttachmentFileName("Starling Foodies’ Nest Fit Out Document_sample_file_name");
		fitOutDoc.setBuildingName(buildingName);
		fitOutDoc.setFitOutDocId(fitOutDocId);
		fitOutDoc.setDisplayName("Starling Foodies’ Nest Fit Out Document");

		fitOutDocDTO = new FitOutDocDTO();
		fitOutDocDTO.setAttachmentFileName(fitOutDocFileName);
		fitOutDocDTO.setDisplayName(fitOutDocDisplayName);

		fitOutDocList = new ArrayList<>();
		fitOutDocList.add(fitOutDoc);

		fitOutDrawingDTO = new FitOutDrawingDTO();
		fitOutDrawingDTO.setAttachmentFileName("FIT_LD__0100000100__" + LocalDate.now().toString() + "_DMS LD.pdf");
		fitOutDrawingDTO.setFitOutDrawingId(fitOutDrawingId);
		fitOutDrawingDTO.getFitOutDocList().add(fitOutDocDTO);
		fitOutDrawingDTO.setIsCompleted(true);

		drawingFile = new MockMultipartFile("data", fitOutDrawingFileName, "application/pdf", "some xml".getBytes());

		bpNumber = "0100000100";
	}

	@After
	public void tearDown() {
		fitOutDrawingDTO = null;
		fitOutWithDrawing = null;
		fitOutWithoutDrawing = null;
		fitOutDocList = null;
		fitOutDoc = null;
		buildingName = null;
		fitOutDrawingId = 0;
		fitOutId = 0;
		fitOutDocId = 0;
		fitOutDrawing = null;
		fitOutDrawingFileName = null;
		fitOutDocFileName = null;
		fitOutDocDisplayName = null;
		bpNumber = null;
	}

	@Test
	public void getFitOutDrawing_EmptyFitOut_ShouldReturnEmptyDrawingButOneDocument() {
		when(fitOutRepository.findOne(anyInt())).thenReturn(null);
		when(fitOutDocRepository.findByBuildingNameIgnoreCase(anyString())).thenReturn(fitOutDocList);
		FitOutDrawingDTO fitOutDrawingDTO = fitOutDrawingService.getFitOutDrawing(fitOutId, buildingName);
		assertEquals(null, fitOutDrawingDTO.getAttachmentFileName());
		assertEquals(1, fitOutDrawingDTO.getFitOutDocList().size());
		assertEquals(0, fitOutDrawingDTO.getFitOutDrawingId());
		assertEquals(null, fitOutDrawingDTO.getIsCompleted());
	}

	@Test
	public void getFitOutDrawing_OneFitOutWithoutDrawing_ShouldReturnEmptyDrawingButOneDocument() {
		when(fitOutRepository.findOne(anyInt())).thenReturn(fitOutWithoutDrawing);
		when(fitOutDocRepository.findByBuildingNameIgnoreCase(anyString())).thenReturn(fitOutDocList);
		FitOutDrawingDTO fitOutDrawingDTO = fitOutDrawingService.getFitOutDrawing(fitOutId, buildingName);
		assertEquals(null, fitOutDrawingDTO.getAttachmentFileName());
		assertEquals(1, fitOutDrawingDTO.getFitOutDocList().size());
		assertEquals(0, fitOutDrawingDTO.getFitOutDrawingId());
		assertEquals(null, fitOutDrawingDTO.getIsCompleted());
	}

	@Test
	public void getFitOutDrawing_OneFitOutWithDrawing_ShouldReturnOneDrawing() {
		when(fitOutRepository.findOne(anyInt())).thenReturn(fitOutWithDrawing);
		when(fitOutDocRepository.findByBuildingNameIgnoreCase(anyString())).thenReturn(fitOutDocList);
		FitOutDrawingDTO fitOutDrawingDTO = fitOutDrawingService.getFitOutDrawing(fitOutId, buildingName);
		assertEquals(fitOutDrawingFileName, fitOutDrawingDTO.getAttachmentFileName());
		assertEquals(1, fitOutDrawingDTO.getFitOutDocList().size());
		assertEquals(fitOutId, fitOutDrawingDTO.getFitOutDrawingId());
		assertEquals(true, fitOutDrawingDTO.getIsCompleted());
	}

	@Test(expected = NoEntityFoundException.class)
	public void saveFitOutDrawing_NullFitOut_ShouldThrowError() throws DmsException, NoEntityFoundException {
		when(fitOutRepository.findOne(anyInt())).thenReturn(null);
		ResponseStatus responseStatus = fitOutDrawingService.saveFitOutDrawing(fitOutId, bpNumber, true, drawingFile);
		assertEquals(null, responseStatus);
	}

	@Test
	public void saveFitOutDrawing_FitOutWithoutDrawing_ShouldReturnSuccess() throws DmsException, NoEntityFoundException {
		when(fitOutRepository.findOne(anyInt())).thenReturn(fitOutWithoutDrawing);
		when(fitOutDrawingRepository.saveAndFlush(any(FitOutDrawing.class))).thenReturn(null);
		when(dmsService.uploadFile(anyString(), any(MultipartFile.class))).thenReturn(new ResponseStatus(HttpStatus.OK, "SUCCESS"));
		ResponseStatus responseStatus = fitOutDrawingService.saveFitOutDrawing(fitOutId, bpNumber, true, drawingFile);
		assertEquals(HttpStatus.OK.toString(), responseStatus.getStatusCode());
	}

	@Test
	public void saveFitOutDrawing_FitOutWithDrawing_ShouldReturnSuccess() throws DmsException, NoEntityFoundException {
		when(fitOutRepository.findOne(anyInt())).thenReturn(fitOutWithDrawing);
		when(fitOutDrawingRepository.saveAndFlush(any(FitOutDrawing.class))).thenReturn(null);
		when(dmsService.uploadFile(anyString(), any(MultipartFile.class))).thenReturn(new ResponseStatus(HttpStatus.OK, "SUCCESS"));
		ResponseStatus responseStatus = fitOutDrawingService.saveFitOutDrawing(fitOutId, bpNumber, true, drawingFile);
		assertEquals(HttpStatus.OK.toString(), responseStatus.getStatusCode());
	}

}
