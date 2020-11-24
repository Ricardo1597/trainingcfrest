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

import com.accenture.trainingcf.dto.ClientsTO;
import com.accenture.trainingcf.service.ClientsService;

@RestController
@RequestMapping("/Client")
public class ClientsController {
	
	@Autowired
	ClientsService service;
	
	@GetMapping("")
	public List<ClientsTO> findAll(@RequestParam(value="keyword", required=false) String keyword){
		return service.findAll(keyword);
	}
	
	@GetMapping("{clientId}")
	public ClientsTO findById(@PathVariable("clientId") String id, String name){
		return service.findById(id);
	}
	
	@PostMapping("")
	public ClientsTO postClient(@RequestBody ClientsTO client) {
		return service.save(client);
	}
	
	@PutMapping("{clientId}")
	public ClientsTO updateClient(@PathVariable("clientId") String id, @RequestBody ClientsTO client) {
		if(!id.equals(client.getId())){
			return new ClientsTO();
		}
		return service.save(client);
	}
	
	@DeleteMapping("{clientId}")
	public String deleteProduct(@PathVariable("clientId") String id){
		return Boolean.toString(service.delete(id));
	}

}
