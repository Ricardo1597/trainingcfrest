package com.accenture.trainingcf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.trainingcf.domain.SalesOrderItemEntity;

@Repository
public interface SalesOrderItemRepository extends JpaRepository<SalesOrderItemEntity, String>{


}
