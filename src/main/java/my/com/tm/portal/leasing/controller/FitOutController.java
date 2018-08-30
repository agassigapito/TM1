package my.com.tm.portal.leasing.controller;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiParam;
import my.com.tm.portal.leasing.dto.DownloadFileDTO;
import my.com.tm.portal.leasing.dto.ListWrapperDTO;
import my.com.tm.portal.leasing.dto.SingleWrapperDTO;
import my.com.tm.portal.leasing.dto.fitout.FitOutContratorInfoDTO;
import my.com.tm.portal.leasing.dto.fitout.FitOutDetailsDTO;
import my.com.tm.portal.leasing.dto.fitout.FitOutDrawingDTO;
import my.com.tm.portal.leasing.dto.fitout.FitOutSafetyBriefingDTO;
import my.com.tm.portal.leasing.exception.DmsException;
import my.com.tm.portal.leasing.exception.NoEntityFoundException;
import my.com.tm.portal.leasing.model.ResponseStatus;
import my.com.tm.portal.leasing.param.DocumentParam;
import my.com.tm.portal.leasing.param.FitOutWorkParam;
import my.com.tm.portal.leasing.param.PreOpeningInspectionParam;
import my.com.tm.portal.leasing.param.SafetyBriefingParam;
import my.com.tm.portal.leasing.service.DmsService;
import my.com.tm.portal.leasing.service.fitout.FitOutDrawingService;
import my.com.tm.portal.leasing.service.fitout.FitOutPreOpeningInspectionService;
import my.com.tm.portal.leasing.service.fitout.FitOutSafetyBriefingService;
import my.com.tm.portal.leasing.service.fitout.FitOutService;
import my.com.tm.portal.leasing.service.fitout.FitOutWorkService;

/**
 * A controller that will call the business logic and implementation for fit-out
 * Request.
 */
@RequestMapping("/api")
@RestController
public class FitOutController {

	@Autowired
	private FitOutService fitoutService;
	@Autowired
	private FitOutDrawingService fitOutDrawingService;
	@Autowired
	private FitOutSafetyBriefingService fitOutSafetyBriefingService;
	@Autowired
	private FitOutWorkService fitOutWorkService;
	@Autowired
	private FitOutPreOpeningInspectionService fitOutPreOpeningInspectionService;
	@Autowired
	private DmsService dmsService;
	/**
	 * This method is used to call the service implementation of getting building
	 * details from table.
	 *
	 * @return ResponseEntity Represent the entire HTTP response it will contain
	 *         status, headers and body.
	 */

	@GetMapping(value = "/fit-out/fit-out-overview-details", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<FitOutDetailsDTO> getFitOutOverviewDetails(String referenceNo) {
		return new ResponseEntity<>(fitoutService.getFitOutOverviewDetails(referenceNo), OK);
	}
	
	@PostMapping(value = "/fit-out/save-contractor-info", consumes = { APPLICATION_JSON_VALUE, MULTIPART_FORM_DATA_VALUE })
	public SingleWrapperDTO<String> saveContractorInfo(List<FitOutContratorInfoDTO> contratorInfo) {
		return fitoutService.saveContractorInfo(contratorInfo);
	}

	@PostMapping(value = "/fit-out/leasing-drawing", consumes = { APPLICATION_JSON_VALUE, MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<ResponseStatus> saveFitOutDrawing(@RequestParam Integer fitOutId,
			@ApiParam(value = "0100000100") @RequestParam String bpNumber, @RequestParam boolean isCompleted,
			@RequestPart MultipartFile file) throws DmsException, NoEntityFoundException {
		return new ResponseEntity<>(fitOutDrawingService.saveFitOutDrawing(fitOutId, bpNumber, isCompleted, file), OK);
	}

	@GetMapping(value = "/fit-out/leasing-drawing/{fitOutId}/{buildingName}", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<FitOutDrawingDTO> getFitOutDrawing(@ApiParam(value = "1000") @PathVariable Integer fitOutId,
			@ApiParam(value = "Starling or\n Uptown") @PathVariable String buildingName) {
		return new ResponseEntity<>(fitOutDrawingService.getFitOutDrawing(fitOutId, buildingName), OK);
	}

	@GetMapping(value = "/fit-out/safety-briefing-list", produces = APPLICATION_JSON_VALUE)
	public ListWrapperDTO<FitOutSafetyBriefingDTO> getSafetyBriefingList(@RequestParam final Integer fitOutId) {
		return fitOutSafetyBriefingService.getSafetyBriefingList(fitOutId);
	}

	@PostMapping(value = "/fit-out/save-safety-briefing", produces = APPLICATION_JSON_VALUE)
	public SingleWrapperDTO<Integer> saveSafetyBriefing(@RequestBody final SafetyBriefingParam param) {
		return fitOutSafetyBriefingService.saveSafetyBriefing(param);
	}

	@PostMapping(value = "/fit-out/save-fit-out-work", produces = APPLICATION_JSON_VALUE)
	public SingleWrapperDTO<Integer> saveFitOutWork(@RequestBody final FitOutWorkParam param) {
		return fitOutWorkService.saveFitOutWork(param);
	}

	@PostMapping(value = "/fit-out/save-pre-opening-inspection", produces = APPLICATION_JSON_VALUE)
	public SingleWrapperDTO<Integer> savePreOpeningInspection(@RequestBody final PreOpeningInspectionParam param) {
		return fitOutPreOpeningInspectionService.savePreOpeningInspection(param);
	}

	@GetMapping(value = "/fit-out/dms-file", produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<DownloadFileDTO> downloadFile(@Validated @RequestBody DocumentParam param) throws NoEntityFoundException {
		return dmsService.downloadImageByFileName(param);
	}
}
