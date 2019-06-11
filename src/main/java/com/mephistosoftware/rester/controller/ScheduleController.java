package com.mephistosoftware.rester.controller;

import com.mephistosoftware.rester.exception.ResourceNotFoundException;
import com.mephistosoftware.rester.model.Schedule;
import com.mephistosoftware.rester.repository.LocationRepository;
import com.mephistosoftware.rester.repository.PersonRepository;
import com.mephistosoftware.rester.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
public class ScheduleController {

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private LocationRepository locationRepository;

	@GetMapping("/schedules")
	public List<Schedule> getSchedules() {
		return scheduleRepository.findAll();
	}

	@PostMapping("/schedules/{employeeId}/{locationId}")
	public Schedule addSchedule(@Valid @RequestBody Schedule schedule, @PathVariable Long employeeId,
			@PathVariable Long locationId) {
		return personRepository.findById(employeeId).map(employee -> {
			schedule.setEmployee(employee);
			return locationRepository.findById(locationId).map(location -> {
				schedule.setLocation(location);
				return scheduleRepository.save(schedule);
			}).orElseThrow(() -> new ResourceNotFoundException("Location not found with id " + locationId));
		}).orElseThrow(() -> new ResourceNotFoundException("Person not found with id " + employeeId));
	}

	@PutMapping("/schedules/{scheduleId}")
	public Schedule updateSchedule(@PathVariable Long scheduleId, @Valid @RequestBody Schedule scheduleRequest) {
		return scheduleRepository.findById(scheduleId).map(schedule -> {
			return scheduleRepository.save(schedule);
		}).orElseThrow(() -> new ResourceNotFoundException("Schedule not found with id " + scheduleId));
	}

	@DeleteMapping("/schedules/{scheduleId}")
	public ResponseEntity<?> deleteSchedule(@PathVariable Long scheduleId) {
		return scheduleRepository.findById(scheduleId).map(schedule -> {
			scheduleRepository.delete(schedule);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Schedule not found with id " + scheduleId));

	}
}
