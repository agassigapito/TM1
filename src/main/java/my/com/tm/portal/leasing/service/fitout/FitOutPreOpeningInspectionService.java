/**
 * 
 */
package my.com.tm.portal.leasing.service.fitout;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import my.com.tm.portal.leasing.constant.Constants;
import my.com.tm.portal.leasing.constant.ErrorMessage;
import my.com.tm.portal.leasing.dto.SingleWrapperDTO;
import my.com.tm.portal.leasing.entity.FitOut;
import my.com.tm.portal.leasing.entity.FitOutPreOpeningInspection;
import my.com.tm.portal.leasing.model.ResponseStatus;
import my.com.tm.portal.leasing.param.PreOpeningInspectionParam;
import my.com.tm.portal.leasing.repository.FitOutPreOpeningInspectionRepository;
import my.com.tm.portal.leasing.repository.FitOutRepository;

/**
 * @author ariel.bisnar
 *
 */
@Service
public class FitOutPreOpeningInspectionService {

	@Autowired
	private FitOutRepository fitOutRepository;
	@Autowired
	private FitOutPreOpeningInspectionRepository fitOutPreOpeningInspectionRepository;

	/**
	 * This method is used to save Pre-Opening Inspection to DB table.
	 *
	 * @param param
	 * 		object parameter representing Pre-Opening Inspection entity to be saved
	 * @return SingleWrapperDTO<Integer>
	 * 		return object that contains Response Status and the ID of saved Pre-Opening Inspection
	 */
	@Transactional
	public SingleWrapperDTO<Integer> savePreOpeningInspection(final PreOpeningInspectionParam param) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.SOA_DATE_TIME_FORMAT);
		SingleWrapperDTO<Integer> response = new SingleWrapperDTO<>();

		final Integer fitOutId = param.getFitOutId();
		if (fitOutId != null) {
			final FitOut fitOut = fitOutRepository.findOne(fitOutId);

			final List<FitOutPreOpeningInspection> preOpeningInspectionList = fitOut.getFitOutPreOpeningInspectionList();
			if (CollectionUtils.isNotEmpty(preOpeningInspectionList)) {
				preOpeningInspectionList.stream().forEach(existingInspection -> existingInspection.setLatest(false));
				fitOutPreOpeningInspectionRepository.save(preOpeningInspectionList);
			}

			FitOutPreOpeningInspection inspection = new FitOutPreOpeningInspection();
			inspection.setLatest(true);
			if (StringUtils.isNotBlank(param.getAppointmentDateTime())) inspection.setAppointmentDateTime(
					LocalDateTime.parse(param.getAppointmentDateTime(), formatter));
			if (StringUtils.isNotBlank(param.getProposedDateTime())) inspection.setProposedDateTime(
					LocalDateTime.parse(param.getProposedDateTime(), formatter));
			inspection.setFitOutTeamRemarks(param.getFitOutTeamRemarks());
			inspection.setTenantRemarks(param.getTenantRemarks());
			inspection.setLastModifiedBy(param.getLastModifiedBy());
			inspection.setLastModifiedDateTime(new Date());
			inspection.setFitOut(fitOut);

			inspection = fitOutPreOpeningInspectionRepository.saveAndFlush(inspection);
			response.setResponseEntity(inspection.getPreOpeningInspectionId());
		} else {
			response.setResponseStatus(new ResponseStatus(HttpStatus.BAD_REQUEST, ErrorMessage.MISSING_REQUIRED_PARAMETER));
		}

		return response;
	}

}
