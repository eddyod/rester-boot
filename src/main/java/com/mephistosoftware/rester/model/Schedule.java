package com.mephistosoftware.rester.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "schedule")
public class Schedule extends AuditModel {
	
	private static final long serialVersionUID = 1L;
	
    private Date start;
    private Date end;
    private BigDecimal payRate;
    private Boolean completed;
    private Person person;
    private Location location;
    
    public Schedule() {}
    
	
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy HH-mm")
    @NotNull
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start", nullable = false)
    public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

    @NotNull
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end", nullable = false)
	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

    @NotNull
    @Column(name = "pay_rate", nullable = false)
	public BigDecimal getPayRate() {
		return payRate;
	}

	public void setPayRate(BigDecimal payRate) {
		this.payRate = payRate;
	}

    @NotNull
	@Column(name = "completed", nullable = false)
	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false, updatable = false, insertable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

    @NotNull
	@Column(name = "location_id", nullable = false)
	public Long getLocationId() {
		return locationId;
	}


	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false, updatable = false, insertable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
    
}
