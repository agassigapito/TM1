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
import my.com.tm.portal.leasing.dto.ListWrapperDTO;
import my.com.tm.portal.leasing.dto.SingleWrapperDTO;
import my.com.tm.portal.leasing.dto.fitout.FitOutSafetyBriefingDTO;
import my.com.tm.portal.leasing.entity.FitOut;
import my.com.tm.portal.leasing.entity.FitOutSafetyBriefing;
import my.com.tm.portal.leasing.model.ResponseStatus;
import my.com.tm.portal.leasing.param.SafetyBriefingParam;
import my.com.tm.portal.leasing.repository.FitOutRepository;
import my.com.tm.portal.leasing.repository.FitOutSafetyBriefingRepository;

/**
 * A service that will be used to handle business logic and implementation for
 * Fit Out Safety Briefing
 *
 * @author ariel.bisnar
 *
 */
@Service
public class FitOutSafetyBriefingService {

	@Autowired
	private FitOutSafetyBriefingRepository fitOutSafetyBriefingRepository;
	@Autowired
	private FitOutRepository fitOutRepository;

	/**
	 * This method is used to retrieve list of FitOutSafetyBriefing.
	 *
	 * @param fitOutId
	 * 		used ID of Fit Out for finding list of Safety Briefing
	 * @return ListWrapperDTO<FitOutSafetyBriefingDTO>
	 * 		return object that contains Response Status and List of Fit Out Safety Briefing
	 */
	public ListWrapperDTO<FitOutSafetyBriefingDTO> getSafetyBriefingList(final Integer fitOutId) {
		final List<FitOutSafetyBriefing> fitOutSafetyBriefingList =
				fitOutSafetyBriefingRepository.findByFitOutFitOutId(fitOutId);
		ListWrapperDTO<FitOutSafetyBriefingDTO> response = new ListWrapperDTO<>();
		if (CollectionUtils.isNotEmpty(fitOutSafetyBriefingList)) {
			for (final FitOutSafetyBriefing fitOutSafetyBriefing : fitOutSafetyBriefingList) {
				FitOutSafetyBriefingDTO fitOutSafetyBriefingDTO = new FitOutSafetyBriefingDTO();
				fitOutSafetyBriefingDTO.setAppointmentDateTime(fitOutSafetyBriefing.getAppointmentDateTime()
						!= null ? fitOutSafetyBriefing.getAppointmentDateTime().toString() : "");
				fitOutSafetyBriefingDTO.setFitOutTeamRemarks(fitOutSafetyBriefing.getFitOutTeamRemarks());
				fitOutSafetyBriefingDTO.setLatest(fitOutSafetyBriefing.isLatest());
				fitOutSafetyBriefingDTO.setProposedDateTime(fitOutSafetyBriefing.getProposedDateTime()
						!= null ? fitOutSafetyBriefing.getProposedDateTime().toString() : "");
				fitOutSafetyBriefingDTO.setTenantRemarks(fitOutSafetyBriefing.getTenantRemarks());
				response.getList().add(fitOutSafetyBriefingDTO);
			}
		}
		return response;
	}

	/**
	 * This method is used to save Safety Briefing to DB table.
	 *
	 * @param param
	 * 		object parameter representing Safety Briefing entity to be saved
	 * @return SingleWrapperDTO<Integer>
	 * 		return object that contains Response Status and the ID of saved SafetyBriefing
	 */
	@Transactional
	public SingleWrapperDTO<Integer> saveSafetyBriefing(final SafetyBriefingParam param) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.SOA_DATE_TIME_FORMAT);
		SingleWrapperDTO<Integer> response = new SingleWrapperDTO<>();

		final Integer fitOutId = param.getFitOutId();
		if (fitOutId != null) {
			final FitOut fitOut = fitOutRepository.findOne(fitOutId);

			final List<FitOutSafetyBriefing> safetyBriefingList = fitOut.getFitOutSafetyBriefingList();
			if (CollectionUtils.isNotEmpty(safetyBriefingList)) {
				safetyBriefingList.stream().forEach(existingSafetyBriefing -> existingSafetyBriefing.setLatest(false));
				fitOutSafetyBriefingRepository.save(safetyBriefingList);
			}

			FitOutSafetyBriefing safetyBriefing = new FitOutSafetyBriefing();
			safetyBriefing.setLatest(true);
			if (StringUtils.isNotBlank(param.getAppointmentDateTime())) safetyBriefing.setAppointmentDateTime(
					LocalDateTime.parse(param.getAppointmentDateTime(), formatter));
			if (StringUtils.isNotBlank(param.getProposedDateTime())) safetyBriefing.setProposedDateTime(
					LocalDateTime.parse(param.getProposedDateTime(), formatter));
			safetyBriefing.setFitOutTeamRemarks(param.getFitOutTeamRemarks());
			safetyBriefing.setTenantRemarks(param.getTenantRemarks());
			safetyBriefing.setLastModifiedBy(param.getLastModifiedBy());
			safetyBriefing.setLastModifiedDateTime(new Date());
			safetyBriefing.setFitOut(fitOut);

			safetyBriefing = fitOutSafetyBriefingRepository.saveAndFlush(safetyBriefing);
			response.setResponseEntity(safetyBriefing.getSafetyBriefingId());
		} else {
			response.setResponseStatus(new ResponseStatus(HttpStatus.BAD_REQUEST, ErrorMessage.MISSING_REQUIRED_PARAMETER));
		}

		return response;
	}

}
