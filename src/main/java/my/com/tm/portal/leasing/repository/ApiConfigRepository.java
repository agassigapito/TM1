package my.com.tm.portal.leasing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import my.com.tm.portal.leasing.entity.ApiConfig;

@Repository
public interface ApiConfigRepository extends JpaRepository<ApiConfig,Long>{

	ApiConfig findById(Long id);
	ApiConfig findByInterfaceId(String interfaceId);

}
