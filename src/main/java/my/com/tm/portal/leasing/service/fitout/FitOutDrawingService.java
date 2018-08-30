package my.com.tm.portal.leasing.service.fitout;

import static my.com.tm.portal.leasing.constant.Constants.DOC_TYPE_FIT;
import static my.com.tm.portal.leasing.constant.Constants.FILE_TYPE_PDF;
import static my.com.tm.portal.leasing.constant.Constants.SERVICE_TYPE_LD;
import static my.com.tm.portal.leasing.constant.Constants.SHORT_DESC_LD;
import static my.com.tm.portal.leasing.constant.Constants.UNDERSCORE;
import static my.com.tm.portal.leasing.constant.ErrorMessage.NO_RECORDS_FOUND;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

/**
 *
 * @author agassi.d.h.agapito
 *
 */
@Service
public class FitOutDrawingService {

	@Autowired
	private FitOutDrawingRepository fitOutDrawingRepository;
	@Autowired
	private FitOutRepository fitOutRepository;
	@Autowired
	private DmsService dmsService;
	@Autowired
	private FitOutDocRepository fitOutDocRepository;

	/**
	 * This method is to used to get the Leasing Drawing and Fit Out Documents for
	 * Fit Out - Leasing Drawing Tab in public portal.
	 *
	 * @author agassi.d.h.agapito
	 * @param fitOutId
	 * @param buildingName
	 * @return FitOutDrawingDTO
	 */
	public FitOutDrawingDTO getFitOutDrawing(final Integer fitOutId, final String buildingName) {

		FitOutDrawingDTO fitOutDrawingDTO = new FitOutDrawingDTO();

		if (fitOutId != null) {

			final FitOut fitOut = fitOutRepository.findOne(fitOutId);

			if (fitOut != null && fitOut.getFitOutDrawing() != null) {
				BeanUtils.copyProperties(fitOut.getFitOutDrawing(), fitOutDrawingDTO);
			}

			final List<FitOutDoc> fitOutDocList = fitOutDocRepository.findByBuildingNameIgnoreCase(buildingName);
			if (CollectionUtils.isNotEmpty(fitOutDocList)) {

				for (FitOutDoc fitOutDoc : fitOutDocList) {

					FitOutDocDTO fitOutDocDTO = new FitOutDocDTO();
					BeanUtils.copyProperties(fitOutDoc, fitOutDocDTO);

					fitOutDrawingDTO.getFitOutDocList().add(fitOutDocDTO);
				}
			}
		}

		return fitOutDrawingDTO;
	}

	/**
	 * This method is used to create or update a FitOutDrawing and upload it in the
	 * DMS. This is implemented in Admin Portal.
	 *
	 * @author agassi.d.h.agapito
	 * @param fitOutId
	 * @param bpNumber
	 * @param isCompleted
	 * @param multipartFile
	 * @return ResponseStatus
	 * @throws DmsException
	 * @throws NoEntityFoundException
	 */
	public ResponseStatus saveFitOutDrawing(final Integer fitOutId, final String bpNumber, final boolean isCompleted,
			final MultipartFile multipartFile) throws DmsException, NoEntityFoundException {

		FitOutDrawing fitOutDrawing = new FitOutDrawing();
		final FitOut fitOut = fitOutRepository.findOne(fitOutId);

		String attachmentFileName = "";

		if (fitOut == null) {
			throw new NoEntityFoundException(NO_RECORDS_FOUND);
		} else {
			if (fitOut.getFitOutDrawing() == null) {

				attachmentFileName = generateFileName(bpNumber);
				fitOutDrawing.setAttachmentFileName(attachmentFileName);
				fitOutDrawing.setIsCompleted(isCompleted);
				fitOutDrawing.setFitOut(fitOutRepository.findOne(fitOutId));
				fitOutDrawing.setFileType(FILE_TYPE_PDF);

			} else {
				fitOutDrawing = fitOut.getFitOutDrawing();
				attachmentFileName = fitOutDrawing.getAttachmentFileName();
			}
			fitOutDrawingRepository.saveAndFlush(fitOutDrawing);
		}
		return dmsService.uploadFile(attachmentFileName, multipartFile);

	}

	/**
	 * This method is used to generate the file name for a FitOut - Leasing Drawing
	 * which is uploaded from Admin Portal.
	 *
	 * @param bpNumber
	 * @return
	 */
	protected String generateFileName(String bpNumber) {

		StringBuilder builder = new StringBuilder();

		builder.append(DOC_TYPE_FIT);
		builder.append(UNDERSCORE);
		builder.append(SERVICE_TYPE_LD);
		builder.append(UNDERSCORE);
		builder.append(UNDERSCORE);
		builder.append(bpNumber);
		builder.append(UNDERSCORE);
		builder.append(UNDERSCORE);
		builder.append(LocalDate.now().toString().replaceAll(UNDERSCORE, ""));
		builder.append(UNDERSCORE);
		builder.append(SHORT_DESC_LD);
		builder.append(FILE_TYPE_PDF);

		return builder.toString();
	}
}
