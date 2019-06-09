package com.mephistosoftware.rester.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mephistosoftware.rester.model.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
	
}
