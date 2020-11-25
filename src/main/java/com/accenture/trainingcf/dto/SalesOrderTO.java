package com.accenture.trainingcf.dto;

import java.util.List;

public class SalesOrderTO {
	private String id;
	private String status;
	private UsersTO user;
	private ClientsTO client;	
	private String createdAt;
	private String createdBy;
	private String modifiedAt;
	private String modifiedBy;
	private List<SalesOrderItemTO> items;
	
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
	public UsersTO getUser() {
		return user;
	}
	public void setUser(UsersTO user) {
		this.user = user;
	}
	public ClientsTO getClient() {
		return client;
	}
	public void setClient(ClientsTO client) {
		this.client = client;
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
	public List<SalesOrderItemTO> getItems() {
		return items;
	}
	public void setItems(List<SalesOrderItemTO> items) {
		this.items = items;
	}
	
}
