package com.mephistosoftware.rester.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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
	private Boolean active = Boolean.TRUE;
	private Integer personType;
	
	
	public Person() {}
	
	@JsonIgnore
	@JsonProperty(value = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Column(name = "first_name", nullable = false)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", nullable = false)
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


	@Column(name = "is_active", nullable = false, columnDefinition = "boolean default true")
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Integer getPersonType() {
		return personType;
	}

	public void setPersonType(Integer personType) {
		this.personType = personType;
	}


}
