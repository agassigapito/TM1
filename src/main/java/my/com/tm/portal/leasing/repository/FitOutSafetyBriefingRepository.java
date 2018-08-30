package my.com.tm.portal.leasing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import my.com.tm.portal.leasing.entity.FitOutSafetyBriefing;

@Repository
public interface FitOutSafetyBriefingRepository extends JpaRepository<FitOutSafetyBriefing, Integer> {

	List<FitOutSafetyBriefing> findByFitOutFitOutId(Integer fitOutId);

}
