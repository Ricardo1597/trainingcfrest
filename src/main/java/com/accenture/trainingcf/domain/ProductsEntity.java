package com.accenture.trainingcf.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "\"TRAINING_PRODUCTS_TBLPRODUCTS\"")
public class ProductsEntity {
	
	@Id
	@Column(name = "\"ID\"")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	@Column(name = "\"NAME\"")
	private String name;
	
	@Column(name = "\"MANUFACTURER\"")
	private String manufacturer;
	
	@Column(name = "\"SALESPRICE\"")
	private Double salesPrice;
	
	@Column(name = "\"BASEPRICE\"")
	private Double basePrice;
	
	@Column(name = "\"QUANTITY\"")
	private Integer quantity;
	
	@Column(name = "\"CREATEDAT\"")
	private LocalDateTime createdAt;
	
	@Column(name = "\"CREATEDBY\"")
	private String createdBy;
	
	@Column(name = "\"MODIFIEDAT\"")
	private LocalDateTime modifiedAt;
	
	@Column(name = "\"MODIFIEDBY\"")
	private String modifiedBy;
	
	@Column(name = "\"VALIDFROM\"")
	private LocalDateTime validFrom;
	
	@Column(name = "\"VALIDTO\"")
	private LocalDateTime validTo;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

}
