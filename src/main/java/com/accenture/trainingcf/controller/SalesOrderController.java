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

import com.accenture.trainingcf.dto.SalesOrderTO;
import com.accenture.trainingcf.dto.SalesOrderItemTO;
import com.accenture.trainingcf.service.SalesOrderService;

@RestController
@RequestMapping("/SalesOrder")
public class SalesOrderController {
	
	@Autowired
	SalesOrderService service;
	
	@GetMapping("")
	public List<SalesOrderTO> findAll(@RequestParam(value="keyword", required=false) String keyword){
		return service.findAll(keyword);
	}
	
	@GetMapping("{salesOrderId}")
	public SalesOrderTO findById(@PathVariable("salesOrderId") String id, String name){
		return service.findById(id);
	}
	
	@PostMapping("")
	public SalesOrderTO postSalesOrder(@RequestBody SalesOrderTO salesOrder) {
		return service.save(salesOrder);
	}
	
	@PutMapping("{salesOrderId}")
	public SalesOrderTO updateSalesOrder(@PathVariable("salesOrderId") String id, @RequestBody SalesOrderTO salesOrder) {
		if(!id.equals(salesOrder.getId())){
			return new SalesOrderTO();
		}
		return service.save(salesOrder);
	}
	
	@DeleteMapping("{salesOrderId}")
	public String deleteSalesOrder(@PathVariable("salesOrderId") String id){
		return Boolean.toString(service.delete(id));
	}
}
