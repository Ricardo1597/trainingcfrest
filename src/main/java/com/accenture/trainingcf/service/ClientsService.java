package com.accenture.trainingcf.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.trainingcf.domain.ClientsEntity;
import com.accenture.trainingcf.dto.ClientsTO;
import com.accenture.trainingcf.repository.ClientsRepository;

@Service
public class ClientsService {
	
	@Autowired
	ClientsRepository rep;
	
	@Autowired
	ModelMapper mapper;

	public List<ClientsTO> findAll(String keyword){
		List<ClientsEntity> result = null;
		
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
			return mapper.map(item, ClientsTO.class);
		}).collect(Collectors.toList());
	}

	public ClientsTO findById(String id) {
		Optional<ClientsEntity> findById = rep.findById(id);
		if(findById.isPresent()){
			return mapper.map(findById.get(), ClientsTO.class);
		}
		return null;
	}
	
	public ClientsTO save(ClientsTO client){	
		if (Strings.isEmpty(client.getId())) {
			client.setCreatedBy("teste");
			client.setCreatedAt(LocalDateTime.now().toString());
		}
		client.setModifiedBy("teste");
		client.setCreatedAt(LocalDateTime.now().toString());
		ClientsEntity savedEntity = rep.save(mapper.map(client, ClientsEntity.class));
		return mapper.map(savedEntity, ClientsTO.class);
	}
	
	
	public boolean delete(String id){
		if(rep.existsById(id)){
			rep.deleteById(id);
			return true;
		}
		return false;
	}

}
