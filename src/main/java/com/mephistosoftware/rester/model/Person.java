package com.mephistosoftware.rester.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
	private String token;
	private String picture;
	private Integer personType = SecurityConstants.TEACHER;
	private Integer schoolId;
	private Location school;

	public Person() {}
	
	@Column(name = "password")
	@JsonIgnore
	@JsonProperty(value = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@NotNull(message = "First name cannot be empty.")
	@Column(name = "firstname")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@NotNull(message = "Last name cannot be empty.")
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

	@Column(name = "is_active", nullable = false)
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	@Column(name = "token", columnDefinition = "TEXT")
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@NotNull(message = "Person must have a role.")
	@Column(name = "person_type")
	public Integer getPersonType() {
		return personType;
	}

	public void setPersonType(Integer personType) {
		this.personType = personType;
	}
	
	
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
	
	@Transient
	public String getName() {
		StringBuilder name = new StringBuilder("");
		if (this.firstName != null && this.lastName != null) {
			name.append(this.lastName);
			name.append(", ");
			name.append(this.firstName);
		}
		return name.toString();
	}

	@Column(name = "school_id")
	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", referencedColumnName = "id", updatable = false, insertable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})	
	public Location getSchool() {
		return school;
	}

	public void setSchool(Location school) {
		this.school = school;
	}


}
