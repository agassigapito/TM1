package my.com.tm.portal.leasing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import my.com.tm.portal.leasing.entity.FitOut;

@Repository
public interface FitOutRepository extends JpaRepository<FitOut, Integer>{
	FitOut findByRefNo(String refNo);
}
