package com.accenture.trainingcf.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.trainingcf.domain.ProductsEntity;
import com.accenture.trainingcf.dto.ProductsTO;
import com.accenture.trainingcf.repository.ProductsRepository;

@Service
public class ProductsService {
	
	@Autowired
	ProductsRepository rep;
	
	@Autowired
	ModelMapper mapper;
	
	

	public List<ProductsTO> findAll(String keyword){
		List<ProductsEntity> result = null;
		
		if(Strings.isEmpty(keyword)){
			result = rep.findAll();
		} else {
            /*ProductsEntity exampleEntity = new ProductsEntity();
            exampleEntity.setName(name);
            Example example = Example.create(exampleEntity);
            result = rep.findAll(example);*/
			
			result = rep.findByKeyword(keyword);
		}
		
		return result.stream().map( item -> {
			return mapper.map(item, ProductsTO.class);
		}).collect(Collectors.toList());
	}


	public ProductsTO findById(String id) {
		Optional<ProductsEntity> findById = rep.findById(id);
		if(findById.isPresent()){
			return mapper.map(findById.get(), ProductsTO.class);
		}
		return null;
	}
	
	public ProductsTO save(ProductsTO product){		
		if (Strings.isEmpty(product.getId())) {
			product.setCreatedBy("teste");
			product.setCreatedAt(LocalDateTime.now().toString());
		}
		product.setModifiedBy("teste");
		product.setModifiedAt(LocalDateTime.now().toString());
		ProductsEntity savedEntity = rep.save(mapper.map(product, ProductsEntity.class));
		return mapper.map(savedEntity, ProductsTO.class);
	}
	
	public boolean delete(String id){
		if(rep.existsById(id)){
			rep.deleteById(id);
			return true;
		}
		return false;
	}

}