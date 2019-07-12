package com.mephistosoftware.rester.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mephistosoftware.rester.security.SecurityConstants;

@Entity
@Table(name = "person")
public class Person  extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String address;
	private Boolean active = Boolean.TRUE;
	private Integer personType = SecurityConstants.TEACHER;
    private List<Schedule> schedules = new ArrayList<>();

	
	
	public Person() {}
	
	@JsonIgnore
	@JsonProperty(value = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Column(name = "firstname")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "lastname")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Email(message = "Enter a proper email.")
	@Column(name = "email", unique = true)
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "is_active", nullable = false, columnDefinition = "boolean default true")
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Column(name = "person_type")
	public Integer getPersonType() {
		return personType;
	}

	public void setPersonType(Integer personType) {
		this.personType = personType;
	}

	@OneToMany(mappedBy="employee", fetch = FetchType.LAZY)	
	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}
	
	@Transient
	public String getName() {
		StringBuilder name = new StringBuilder("");
		if (this.firstName != null) {
			name.append(this.firstName);
		}
		if (this.lastName != null) {
			name.append(" ");
			name.append(this.lastName);
		}
		return name.toString();
	}


}
