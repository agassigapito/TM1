/**
 * 
 */
package my.com.tm.portal.leasing.service.fitout;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import my.com.tm.portal.leasing.constant.Constants;
import my.com.tm.portal.leasing.constant.ErrorMessage;
import my.com.tm.portal.leasing.dto.SingleWrapperDTO;
import my.com.tm.portal.leasing.entity.FitOutWork;
import my.com.tm.portal.leasing.model.ResponseStatus;
import my.com.tm.portal.leasing.param.FitOutWorkParam;
import my.com.tm.portal.leasing.repository.FitOutRepository;
import my.com.tm.portal.leasing.repository.FitOutWorkRepository;

/**
 * @author ariel.bisnar
 *
 */
@Service
public class FitOutWorkService {

	@Autowired
	private FitOutRepository fitOutRepository;
	@Autowired
	private FitOutWorkRepository fitOutWorkRepository;

	/**
	 * This method is used to save Fit Out Work to DB table.
	 *
	 * @param param
	 * 		object parameter representing Fit Out Work entity to be saved
	 * @return
	 * 		return object that contains Response Status and the ID of saved Fit Out Work
	 */
	public SingleWrapperDTO<Integer> saveFitOutWork(final FitOutWorkParam param) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.SOA_DATE_TIME_FORMAT);
		SingleWrapperDTO<Integer> response = new SingleWrapperDTO<>();

		final Integer fitOutId = param.getFitOutId();
		if (fitOutId != null && fitOutRepository.findOne(fitOutId) != null) {
			FitOutWork fitOutWork = new FitOutWork();
			fitOutWork.setType(param.getType());
			if (StringUtils.isNotBlank(param.getAppointmentDateTime())) fitOutWork.setAppointmentDateTime(
					LocalDateTime.parse(param.getAppointmentDateTime(), formatter));
			if (StringUtils.isNotBlank(param.getProposedDateTime())) fitOutWork.setProposedDateTime(
					LocalDateTime.parse(param.getProposedDateTime(), formatter));
			fitOutWork.setFitOutTeamRemarks(param.getFitOutTeamRemarks());
			fitOutWork.setTenantRemarks(param.getTenantRemarks());
			fitOutWork.setLastModifiedBy(param.getLastModifiedBy());
			fitOutWork.setLastModifiedDateTime(new Date());
			fitOutWork.setFitOut(fitOutRepository.findOne(fitOutId));

			fitOutWork = fitOutWorkRepository.saveAndFlush(fitOutWork);
			response.setResponseEntity(fitOutWork.getWorkId());
		} else {
			response.setResponseStatus(new ResponseStatus(HttpStatus.BAD_REQUEST, ErrorMessage.MISSING_REQUIRED_PARAMETER));
		}
		return response;
	}

}
