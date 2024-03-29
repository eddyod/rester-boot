package com.mephistosoftware.rester.model;

import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "location")
public class Location extends AuditModel {
	
	private static final long serialVersionUID = 1L;

    private String name;
    private String address;
    private String email;
    private String phone;
    private BigDecimal latitude;
    private BigDecimal longitude;
    // private Set<Person> employees = new HashSet<>();

	@NotEmpty(message = "Name cannot be empty")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotEmpty(message = "Address cannot be empty.")
	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	@Email(message = "Enter a proper email.")
	@Column(name = "email", nullable = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Min(value = -86, message = "Minimum value for latitude is -86")
	@Max(value = 86, message = "Maximum value for latitude is 86")
	@Column(name = "latitude", precision = 10, scale = 8)
	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	@Min(value = -180, message = "Minimum value for longitude is -180")
	@Max(value = 180, message = "Maximum value for longitude is 180")
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

	/*
	@ManyToMany(mappedBy = "schools")
	@JsonIgnore
	public Set<Person> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Person> employees) {
		this.employees = employees;
	}
	*/
	
	/*
	@OneToMany(mappedBy="person", fetch = FetchType.LAZY)
	@JsonDeserialize(using = ScheduleDeserializer.class)
	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}
	*/
	
    
}
