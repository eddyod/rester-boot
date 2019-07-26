package com.mephistosoftware.rester.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mephistosoftware.rester.model.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

	@Query(value = "select s from Schedule s where s.person.id = :personId order by s.start desc")
	List<Schedule> findByEmployee(@Param("personId") Long personId);

	@Query(value = "select s from Schedule s where s.location.id = :locationId")
	List<Schedule> findByLocation(@Param("locationId") Long locationId);

	@Query(value = "select s from Schedule s where s.person.id = :personId and DATE(s.start) = DATE(:today) order by s.start desc")
	List<Schedule> findTodayByEmployee(@Param("personId") Long personId, @Param("today") Date today);
	
}
