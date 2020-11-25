package com.accenture.trainingcf.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "\"TRAINING_SALESORDER_TBLSALESORDERITEM\"")
public class SalesOrderItemEntity {
	
	@Id
	@Column(name = "\"ID\"")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	@Column(name = "\"STATUS\"")
	private String status;
	
//	@Column(name = "\"SALESORDER_ID\"")
//	private SalesOrderEntity salesOrderId;
	
	@ManyToOne
	@JoinColumn(name = "\"SALESORDER_ID\"")
	private SalesOrderEntity salesOrder;
	
//	@Column(name = "\"PRODUCT_ID\"")
//	private ProductsEntity productId;
	
	@ManyToOne
	@JoinColumn(name = "\"PRODUCT_ID\"")
	private ProductsEntity product;
	
	@Column(name = "\"CREATEDAT\"")
	private LocalDateTime createdAt;
	
	@Column(name = "\"CREATEDBY\"")
	private String createdBy;
	
	@Column(name = "\"MODIFIEDAT\"")
	private LocalDateTime modifiedAt;
	
	@Column(name = "\"MODIFIEDBY\"")
	private String modifiedBy;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public SalesOrderEntity getSalesOrder() {
		return salesOrder;
	}

	public void setSalesOrder(SalesOrderEntity salesOrder) {
		this.salesOrder = salesOrder;
	}

	public ProductsEntity getProduct() {
		return product;
	}

	public void setProduct(ProductsEntity product) {
		this.product = product;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(LocalDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}
