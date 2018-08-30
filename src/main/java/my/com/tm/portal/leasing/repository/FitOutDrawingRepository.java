package my.com.tm.portal.leasing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import my.com.tm.portal.leasing.entity.FitOut;
import my.com.tm.portal.leasing.entity.FitOutDrawing;

@Repository
public interface FitOutDrawingRepository extends JpaRepository<FitOutDrawing, Integer>{
	FitOutDrawing findByFitOut(FitOut fitOut);
}
