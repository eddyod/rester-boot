package com.mephistosoftware.rester.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")


@Entity
@Table(name = "schedule")
public class Schedule extends AuditModel {
	
	private static final long serialVersionUID = 1L;
	

    private Date start;
    private Date end;
    private BigDecimal payRate;
    private Boolean completed; 
    private Integer personId;
    private Integer locationId;
    
    private Person person;
    private Location location;
    
    public Schedule() {}
    
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    // @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "UTC")
    @NotNull(message = "Start time cannot be empty.")
    @Column(name = "start", nullable = false)
    public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

    @Column(name = "end", nullable = false)
	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

    @Column(name = "pay_rate")
	public BigDecimal getPayRate() {
		return payRate;
	}

	public void setPayRate(BigDecimal payRate) {
		this.payRate = payRate;
	}

	@Column(name = "completed", nullable = true)
	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	@NotNull(message = "A schedule must have a teacher.")
	@Column(name = "person_id", nullable = false)
    public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	@NotNull(message = "A schedule must have a school.")
	@Column(name = "location_id", nullable = false)
	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
	

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", referencedColumnName = "id", updatable = false, insertable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", referencedColumnName = "id", updatable = false, insertable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	public Location getLocation() {
		return location;
	}
    
	public void setLocation(Location location) {
		this.location = location;
	}
	
	@Transient
	public String getTitle() {
		StringBuilder title = new StringBuilder("");
		if (this.person != null && this.location != null) {
			title.append(this.person.getName());
			title.append(" at ");
			title.append(this.location.getName());
		}
		
		return title.toString();
	}
	
}
