package my.com.tm.portal.leasing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import my.com.tm.portal.leasing.entity.FitOutPreOpeningInspection;

@Repository
public interface FitOutPreOpeningInspectionRepository extends JpaRepository<FitOutPreOpeningInspection, Integer> {

}
