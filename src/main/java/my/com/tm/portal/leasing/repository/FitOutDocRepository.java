package my.com.tm.portal.leasing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import my.com.tm.portal.leasing.entity.FitOutDoc;

@Repository
public interface FitOutDocRepository extends JpaRepository<FitOutDoc, Integer>{

	List<FitOutDoc> findByBuildingNameIgnoreCase(String buildingName);
}
