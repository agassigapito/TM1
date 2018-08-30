/**
 * 
 */
package my.com.tm.portal.leasing.service.fitout;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import my.com.tm.portal.leasing.constant.Constants;
import my.com.tm.portal.leasing.constant.ErrorMessage;
import my.com.tm.portal.leasing.dto.ListWrapperDTO;
import my.com.tm.portal.leasing.dto.SingleWrapperDTO;
import my.com.tm.portal.leasing.dto.fitout.FitOutSafetyBriefingDTO;
import my.com.tm.portal.leasing.entity.FitOut;
import my.com.tm.portal.leasing.entity.FitOutSafetyBriefing;
import my.com.tm.portal.leasing.param.SafetyBriefingParam;
import my.com.tm.portal.leasing.repository.FitOutRepository;
import my.com.tm.portal.leasing.repository.FitOutSafetyBriefingRepository;

/**
 * @author ariel.bisnar
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class FitOutSafetyBriefingTest {

	@InjectMocks
	private FitOutSafetyBriefingService fitOutSafetyBriefingService;

	@Mock
	private FitOutSafetyBriefingRepository fitOutSafetyBriefingRepository;

	@Mock
	private FitOutRepository fitOutRepository;

	@Test
	public void testGetSafetyBriefingListWithoutExisting() {
		final ListWrapperDTO<FitOutSafetyBriefingDTO> response = fitOutSafetyBriefingService.getSafetyBriefingList(1000);

		assertEquals(Constants.SUCCESSFUL_RESPONSE.getStatusMessage(), response.getResponseStatus().getStatusMessage());
		assertEquals(Constants.SUCCESSFUL_RESPONSE.getStatusCode(), response.getResponseStatus().getStatusCode());
		assertEquals(0, response.getList().size());
	}

	@Test
	public void testGetSafetyBriefingListWithExisting() {
		FitOutSafetyBriefing fitOutSafetyBriefing = new FitOutSafetyBriefing();
		fitOutSafetyBriefing.setAppointmentDateTime(LocalDateTime.now());
		fitOutSafetyBriefing.setProposedDateTime(LocalDateTime.now());

		List<FitOutSafetyBriefing> safetyBriefingList = new ArrayList<>();
		safetyBriefingList.add(fitOutSafetyBriefing);
		safetyBriefingList.add(new FitOutSafetyBriefing());

		when(fitOutSafetyBriefingRepository.findByFitOutFitOutId(anyInt())).thenReturn(safetyBriefingList);

		final ListWrapperDTO<FitOutSafetyBriefingDTO> response = fitOutSafetyBriefingService.getSafetyBriefingList(1000);

		assertEquals(Constants.SUCCESSFUL_RESPONSE.getStatusMessage(), response.getResponseStatus().getStatusMessage());
		assertEquals(Constants.SUCCESSFUL_RESPONSE.getStatusCode(), response.getResponseStatus().getStatusCode());
		assertEquals(2, response.getList().size());
	}

	@Test
	public void testSaveSafetyBriefingMissingFitOutId() {
		SafetyBriefingParam safetyBriefingParam = new SafetyBriefingParam();
		final SingleWrapperDTO<Integer> response = fitOutSafetyBriefingService.saveSafetyBriefing(safetyBriefingParam);

		assertEquals(ErrorMessage.MISSING_REQUIRED_PARAMETER, response.getResponseStatus().getStatusMessage());
		assertEquals(HttpStatus.BAD_REQUEST.toString(), response.getResponseStatus().getStatusCode());
	}

	@Test
	public void testSaveSafetyBriefing() {
		FitOut fitOut = new FitOut();
		FitOutSafetyBriefing safetyBriefing = new FitOutSafetyBriefing();
		safetyBriefing.setSafetyBriefingId(1001);

		SafetyBriefingParam safetyBriefingParam = new SafetyBriefingParam();
		safetyBriefingParam.setFitOutId(1000);

		when(fitOutRepository.findOne(anyInt())).thenReturn(fitOut);
		when(fitOutSafetyBriefingRepository.saveAndFlush(any())).thenReturn(safetyBriefing);

		final SingleWrapperDTO<Integer> response = fitOutSafetyBriefingService.saveSafetyBriefing(safetyBriefingParam);

		assertEquals(Constants.SUCCESSFUL_RESPONSE.getStatusMessage(), response.getResponseStatus().getStatusMessage());
		assertEquals(Constants.SUCCESSFUL_RESPONSE.getStatusCode(), response.getResponseStatus().getStatusCode());
		assertEquals(Integer.valueOf(1001), response.getResponseEntity());
	}
}
