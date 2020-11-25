package com.accenture.trainingcf.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.trainingcf.domain.UsersEntity;
import com.accenture.trainingcf.dto.UsersTO;
import com.accenture.trainingcf.repository.UsersRepository;

@Service
public class UsersService {
	
	@Autowired
	UsersRepository rep;
	
	@Autowired
	ModelMapper mapper;
	
	

	public List<UsersTO> findAll(String keyword){
		List<UsersEntity> result = null;
		
		if(Strings.isEmpty(keyword)){
			result = rep.findAll();
		} else {			
			result = rep.findByKeyword(keyword);
		}
		
		return result.stream().map( item -> {
			return mapper.map(item, UsersTO.class);
		}).collect(Collectors.toList());
	}


	public UsersTO findById(String id) {
		Optional<UsersEntity> findById = rep.findById(id);
		if(findById.isPresent()){
			return mapper.map(findById.get(), UsersTO.class);
		}
		return null;
	}
	
	public UsersTO save(UsersTO user){	
		if (Strings.isEmpty(user.getId())) {
			user.setCreatedBy("teste");
			user.setCreatedAt(LocalDateTime.now().toString());
		}
		user.setModifiedBy("teste");
		user.setModifiedAt(LocalDateTime.now().toString());	
		UsersEntity savedEntity = rep.save(mapper.map(user, UsersEntity.class));
		return mapper.map(savedEntity, UsersTO.class);
	}
	
	public boolean delete(String id){
		if(rep.existsById(id)){
			rep.deleteById(id);
			return true;
		}
		return false;
	}

}