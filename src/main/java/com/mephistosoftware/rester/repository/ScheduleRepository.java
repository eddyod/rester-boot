package com.mephistosoftware.rester.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mephistosoftware.rester.model.Schedule;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
	
    List<Schedule> findBySiteId(Long siteId);
}
