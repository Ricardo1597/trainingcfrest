package com.accenture.trainingcf.dto;

public class SalesOrderItemTO {
	private String Id;
	private String status;
	private String salesOrderId;
	private ProductsTO product;
	private String createdAt;
	private String createdBy;
	private String modifiedAt;
	private String modifiedBy;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSalesOrderId() {
		return salesOrderId;
	}
	public void setSalesOrderId(String salesOrderId) {
		this.salesOrderId = salesOrderId;
	}
	public ProductsTO getProduct() {
		return product;
	}
	public void setProduct(ProductsTO product) {
		this.product = product;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedAt() {
		return modifiedAt;
	}
	public void setModifiedAt(String modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	
	
}
