package my.com.tm.portal.leasing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import my.com.tm.portal.leasing.entity.FitOut;
import my.com.tm.portal.leasing.entity.FitOutContractorInfo;

@Repository
public interface FitOutContractorInfoRepository extends JpaRepository<FitOutContractorInfo, Integer>{
 List<FitOutContractorInfo> findByFitOut(FitOut fitOut);
}
