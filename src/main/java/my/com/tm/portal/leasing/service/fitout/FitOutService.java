package my.com.tm.portal.leasing.service.fitout;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import my.com.tm.portal.leasing.constant.ErrorMessage;
import my.com.tm.portal.leasing.dto.SingleWrapperDTO;
import my.com.tm.portal.leasing.dto.fitout.FitOutContratorInfoDTO;
import my.com.tm.portal.leasing.dto.fitout.FitOutDetailsDTO;
import my.com.tm.portal.leasing.dto.fitout.FitOutInfoDTO;
import my.com.tm.portal.leasing.entity.FitOut;
import my.com.tm.portal.leasing.entity.FitOutContractorInfo;
import my.com.tm.portal.leasing.model.ResponseStatus;
import my.com.tm.portal.leasing.repository.FitOutContractorInfoRepository;
import my.com.tm.portal.leasing.repository.FitOutRepository;

/**
 * A Service that will be use to handle business logic and implementation for
 * Fit Out.
 */
@Service
public class FitOutService {
	@Autowired
	private FitOutRepository fitOutRepository;

	@Autowired
	private FitOutContractorInfoRepository fitOutContractorInfoRepository;

	/**
	 * A method that is used to get Fit Out Overview Details and Contractor
	 * Information
	 *
	 * @param getFitOutOverviewDetails
	 * @return ResponseEntity
	 */
	public FitOutDetailsDTO getFitOutOverviewDetails(String referenceNo) {

		FitOut fitOut = fitOutRepository.findByRefNo(referenceNo);
		FitOutDetailsDTO fDetails = new FitOutDetailsDTO();

		FitOutInfoDTO fitOutInfoDTO = new FitOutInfoDTO();

		fitOutInfoDTO.setFitOutId(fitOut.getFitOutId());
		fitOutInfoDTO.setRefNo(fitOut.getRefNo());
		fitOutInfoDTO.setVacantPossession(fitOut.getVacantPossession());
		fitOutInfoDTO.setPeriod(fitOut.getFitOutStart() + " - " + fitOut.getFitOutEnd());
		fitOutInfoDTO.setFitOutDuration(fitOut.getFitOutDuration());
		fitOutInfoDTO.setBuildingCode(fitOut.getBuildingCode());
		fitOutInfoDTO.setLevel(fitOut.getLevel());
		fitOutInfoDTO.setDemisedPremises(fitOut.getDemisedPremises());
		fitOutInfoDTO.setLooDate(fitOut.getLooDate());
		fitOutInfoDTO.setAreaSizeLot(fitOut.getAreaSizeLot());
		fitOutInfoDTO.setAreaSizeAlfresco(fitOut.getAreaSizeAlfresco());
		fitOutInfoDTO.setPermittedUse(fitOut.getPermittedUse());
		fitOutInfoDTO.setTradingHours(fitOut.getTradingHours());
		fitOutInfoDTO.setPaymentStatus(fitOut.getPaymentStatus());
		fitOutInfoDTO.setPrincipalPropertyInsurance(fitOut.getPrincipalPropertyInsurance());
		fitOutInfoDTO.setLiabilityInsuranceLimit(fitOut.getLiabilityInsuranceLimit());
		fitOutInfoDTO.setRiskInsurance(fitOut.getRiskInsurance());
		fitOutInfoDTO.setSpecialCondition(fitOut.getSpecialCondition());

		fDetails.setFitOutInfoDTO(fitOutInfoDTO);

		List<FitOutContratorInfoDTO> fitOutContratorInfoDTOList = new ArrayList<>();
		fitOut.getFitOutContractorInfo().forEach(contractor -> {
			FitOutContratorInfoDTO fitOutContrator = new FitOutContratorInfoDTO();
			fitOutContrator.setCompanyName(contractor.getCompanyName());
			fitOutContrator.setContactPerson(contractor.getContactPerson());
			fitOutContrator.setEmailAddress(contractor.getEmailAddress());
			fitOutContrator.setRefNo(fitOut.getRefNo());
			fitOutContrator.setTelephoneNo(contractor.getTelephoneNo());
			fitOutContrator.setMobileNo(contractor.getMobileNo());
			fitOutContrator.setFitOutId(String.valueOf(fitOut.getFitOutId()));
			fitOutContratorInfoDTOList.add(fitOutContrator);
		});

		fDetails.setFitOutContratorInfoDTO(fitOutContratorInfoDTOList);

		return fDetails;
	}

	/**
	 * A method that is used to get Fit Out Overview Details and Contractor
	 * Information
	 *
	 * @param getFitOutOverviewDetails
	 * @return ResponseEntity
	 */
	public SingleWrapperDTO<String> saveContractorInfo(List<FitOutContratorInfoDTO> contratorInfo) {
		SingleWrapperDTO<String> response = new SingleWrapperDTO<>();
		if (CollectionUtils.isNotEmpty(contratorInfo)) {
			FitOut fitOut = new FitOut();
			fitOut.setRefNo(contratorInfo.get(0).getRefNo());
			fitOutContractorInfoRepository.findByFitOut(fitOut);
			contratorInfo.stream().forEach(contInfo -> {
				FitOutContractorInfo fitOutContractorInfo = new FitOutContractorInfo();
				fitOutContractorInfo.setFitOut(fitOut);
				fitOutContractorInfo.setCompanyName(contInfo.getCompanyName());
				fitOutContractorInfo.setContactPerson(contInfo.getContactPerson());
				fitOutContractorInfo.setMobileNo(contInfo.getMobileNo());
				fitOutContractorInfo.setTelephoneNo(contInfo.getTelephoneNo());
				fitOutContractorInfo.setEmailAddress(contInfo.getEmailAddress());
				fitOutContractorInfoRepository.saveAndFlush(fitOutContractorInfo);
			});
			response.setResponseEntity(fitOut.getRefNo());
		} else {
			response.setResponseStatus(
					new ResponseStatus(HttpStatus.BAD_REQUEST, ErrorMessage.MISSING_REQUIRED_PARAMETER));
		}
		return response;
	}
}
