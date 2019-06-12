package com.mephistosoftware.rester.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "location")
public class Location extends AuditModel {
	
	private static final long serialVersionUID = 1L;
	
    private String name;
    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private List<Schedule> schedules = new ArrayList<>();

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
	
	@Column(name = "latitude", precision = 10, scale = 8)
	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	@Column(name = "longitude", precision = 11, scale = 8)
	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@OneToMany(mappedBy="location")
	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}
    
}
