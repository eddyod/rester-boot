package com.mephistosoftware.rester.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "location")
public class Location extends AuditModel {
	
	private static final long serialVersionUID = 1L;
	
    private Long id;
    private String name;
    private String address;

	@NotEmpty(message = "Name cannot be empty")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotEmpty(message = "Address cannot be empty.")
	@Column(name = "address1")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
    
}
