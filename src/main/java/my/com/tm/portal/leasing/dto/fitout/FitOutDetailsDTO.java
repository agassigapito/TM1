package my.com.tm.portal.leasing.dto.fitout;

import java.io.Serializable;
import java.util.List;

public class FitOutDetailsDTO implements Serializable {

	private static final long serialVersionUID = 3243442888841665619L;

	FitOutInfoDTO fitOutInfoDTO;
	
	List<FitOutContratorInfoDTO> fitOutContratorInfoDTO;
	
	List<FitOutSafetyBriefingDTO> fitOutSafetyBriefingDTO;
	
	FitOutDrawingDTO fitOutDrawingDTO;

	/**
	 * @return the fitOutInfoDTO
	 */
	public FitOutInfoDTO getFitOutInfoDTO() {
		return fitOutInfoDTO;
	}

	/**
	 * @param fitOutInfoDTO the fitOutInfoDTO to set
	 */
	public void setFitOutInfoDTO(FitOutInfoDTO fitOutInfoDTO) {
		this.fitOutInfoDTO = fitOutInfoDTO;
	}

	/**
	 * @return the fitOutContratorInfoDTO
	 */
	public List<FitOutContratorInfoDTO> getFitOutContratorInfoDTO() {
		return fitOutContratorInfoDTO;
	}

	/**
	 * @param fitOutContratorInfoDTO the fitOutContratorInfoDTO to set
	 */
	public void setFitOutContratorInfoDTO(List<FitOutContratorInfoDTO> fitOutContratorInfoDTO) {
		this.fitOutContratorInfoDTO = fitOutContratorInfoDTO;
	}

	/**
	 * @return the fitOutSafetyBriefingDTO
	 */
	public List<FitOutSafetyBriefingDTO> getFitOutSafetyBriefingDTO() {
		return fitOutSafetyBriefingDTO;
	}

	/**
	 * @param fitOutSafetyBriefingDTO the fitOutSafetyBriefingDTO to set
	 */
	public void setFitOutSafetyBriefingDTO(List<FitOutSafetyBriefingDTO> fitOutSafetyBriefingDTO) {
		this.fitOutSafetyBriefingDTO = fitOutSafetyBriefingDTO;
	}

	/**
	 * @return the fitOutDrawingDTO
	 */
	public FitOutDrawingDTO getFitOutDrawingDTO() {
		return fitOutDrawingDTO;
	}

	/**
	 * @param fitOutDrawingDTO the fitOutDrawingDTO to set
	 */
	public void setFitOutDrawingDTO(FitOutDrawingDTO fitOutDrawingDTO) {
		this.fitOutDrawingDTO = fitOutDrawingDTO;
	}

}
