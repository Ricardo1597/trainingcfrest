package com.accenture.trainingcf.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.trainingcf.domain.SalesOrderEntity;
import com.accenture.trainingcf.dto.SalesOrderTO;
import com.accenture.trainingcf.repository.SalesOrderRepository;
import com.accenture.trainingcf.repository.SalesOrderItemRepository;

@Service
public class SalesOrderService {
	
	@Autowired
	SalesOrderRepository rep;
	
	@Autowired
	SalesOrderItemRepository itemsRep;
	
	@Autowired
	ModelMapper mapper;
	
	
	public List<SalesOrderTO> findAll(String keyword){
		List<SalesOrderEntity> result = null;
		
		if(Strings.isEmpty(keyword)){
			result = rep.findAll();
		} else {			
			result = rep.findByKeyword(keyword);
		}
		
		return result.stream().map( item -> {
			return mapper.map(item, SalesOrderTO.class);
		}).collect(Collectors.toList());
	}


	public SalesOrderTO findById(String id) {
		Optional<SalesOrderEntity> findById = rep.findById(id);
		if(findById.isPresent()){
			return mapper.map(findById.get(), SalesOrderTO.class);
		}
		return null;
	}
	
	public SalesOrderTO save(SalesOrderTO salesOrder){	
		SalesOrderEntity salesOrderEntity = mapper.map(salesOrder, SalesOrderEntity.class);
				
		if (Strings.isEmpty(salesOrderEntity.getId())) {
			salesOrderEntity.setCreatedBy("teste");
			salesOrderEntity.setCreatedAt(LocalDateTime.now());
		}
		salesOrderEntity.setModifiedBy("teste");
		salesOrderEntity.setCreatedAt(LocalDateTime.now());
		
		SalesOrderEntity savedEntity = rep.save(salesOrderEntity);
		
		salesOrderEntity.getItems().stream().forEach(item -> item.setSalesOrder(savedEntity));
		itemsRep.saveAll(salesOrderEntity.getItems());
		
		return mapper.map(savedEntity, SalesOrderTO.class);
	}
	
	public boolean delete(String id){
		if(rep.existsById(id)){
			rep.deleteById(id);
			return true;
		}
		return false;
	}

}
