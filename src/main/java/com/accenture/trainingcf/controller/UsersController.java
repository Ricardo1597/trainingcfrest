package com.accenture.trainingcf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.trainingcf.dto.UsersTO;
import com.accenture.trainingcf.service.UsersService;

@RestController
@RequestMapping("/User")
public class UsersController {
	
	@Autowired
	UsersService service;
	
	@GetMapping("")
	public List<UsersTO> findAll(@RequestParam(value="keyword", required=false) String keyword){
		return service.findAll(keyword);
	}
	
	@GetMapping("{userId}")
	public UsersTO findById(@PathVariable("userId") String id, String name){
		return service.findById(id);
	}
	
	@PostMapping("")
	public UsersTO postProduct(@RequestBody UsersTO user) {
		return service.save(user);
	}
	
	@PutMapping("{userId}")
	public UsersTO updateProduct(@PathVariable("userId") String id, @RequestBody UsersTO user) {
		if(!id.equals(user.getId())){
			return new UsersTO();
		}
		return service.save(user);
	}
	
	@DeleteMapping("{userId}")
	public String deleteProduct(@PathVariable("userId") String id){
		return Boolean.toString(service.delete(id));
	}

}
