package com.mephistosoftware.rester.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mephistosoftware.rester.model.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

	@Query(value = "select s from Schedule s where s.employee.id = :employeeId")
	List<Schedule> findByEmployee(@Param("employeeId") Long employeeId);

	@Query(value = "select s from Schedule s where s.location.id = :locationId")
	List<Schedule> findByLocation(@Param("locationId") Long locationId);
	
}
