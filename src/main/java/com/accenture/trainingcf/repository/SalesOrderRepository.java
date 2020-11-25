package com.accenture.trainingcf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.accenture.trainingcf.domain.SalesOrderEntity;

@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrderEntity, String>{

	
	@Query("Select k from SalesOrderEntity as k where k.createdBy like :keyword")
    public List<SalesOrderEntity> findByKeyword(@Param("keyword") String keyword);
	
//	@Query(nativeQuery = true, value="<your-join-query>")
//	public Employee getEmployeeAllDetails();
}
