package com.mephistosoftware.rester.controller;

import com.mephistosoftware.rester.exception.ResourceNotFoundException;
import com.mephistosoftware.rester.model.Schedule;
import com.mephistosoftware.rester.repository.LocationRepository;
import com.mephistosoftware.rester.repository.OffsetBasedPageRequest;
import com.mephistosoftware.rester.repository.PersonRepository;
import com.mephistosoftware.rester.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
public class ScheduleController {

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private LocationRepository locationRepository;

	/**
	 * Get all schedules
	 * 
	 * @return list of all the schedules
	 */
	@GetMapping("/schedules")
	public List<Schedule> getSchedules() {
		return scheduleRepository.findAll();
	}

	/**
	 * Gets one schedule by primary ID
	 * 
	 * @param id
	 * @return a schedule object
	 */
	@GetMapping("/schedule/{id}")
	public Schedule getScheduleById(@PathVariable Long id) {
		return scheduleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Schedule not found with id " + id));
	}

	/**
	 * Gets list of schedules by employee
	 * 
	 * @param employeeId
	 * @return a list of schedules per employee
	 */
	@GetMapping("/schedules/employee/{employeeId}")
	public List<Schedule> getSchedulesByEmployee(@PathVariable Long employeeId) {
		return scheduleRepository.findByEmployee(employeeId);
	}

	/**
	 * Gets list of schedules by employee and today
	 * 
	 * @param employeeId
	 * @return a list of schedules per employee and today
	 */
	@GetMapping("/schedules/today/{employeeId}")
	public List<Schedule> getTodaySchedulesByEmployee(@PathVariable Long employeeId) {
		Date today = Calendar.getInstance().getTime();
		return scheduleRepository.findTodayByEmployee(employeeId, today);
	}


	/**
	 * Gets list of schedules by location
	 * 
	 * @param locationId
	 * @return a list of schedules per location
	 */
	@GetMapping("/schedules/location/{locationId}")
	public List<Schedule> getSchedulesByLocation(@PathVariable Long locationId) {
		return scheduleRepository.findByLocation(locationId);
	}

	@PostMapping("/scheduleXXXX/{employeeId}/{locationId}")
	public Schedule createScheduleXXX(@PathVariable Long employeeId,
			@PathVariable Long locationId, @Valid @RequestBody Schedule schedule) {
		return personRepository.findById(employeeId).map(employee -> {
			schedule.setPerson(employee);
			return locationRepository.findById(locationId).map(location -> {
				schedule.setLocation(location);
				schedule.setCompleted(false);
				return scheduleRepository.save(schedule);
			}).orElseThrow(() -> new ResourceNotFoundException("Location not found with id " + locationId));
		}).orElseThrow(() -> new ResourceNotFoundException("Person not found with id " + employeeId));
	}

	@PostMapping("/schedule")
	public Schedule addSchedule(@Valid @RequestBody Schedule schedule) {
		schedule.setCompleted(false);		
		return scheduleRepository.save(schedule);
	}

	@PutMapping("/schedule/{id}/{employeeId}/{locationId}")
	public Schedule putSchedule(@PathVariable Long id, @PathVariable Long employeeId, @PathVariable Long locationId, 
			@Valid @RequestBody Schedule schedule) {
		return personRepository.findById(employeeId).map(employee -> {
			schedule.setPerson(employee);
			return locationRepository.findById(locationId).map(location -> {
				schedule.setLocation(location);
				return scheduleRepository.save(schedule);
			}).orElseThrow(() -> new ResourceNotFoundException("Location not found with id " + locationId));
		}).orElseThrow(() -> new ResourceNotFoundException("Person not found with id " + employeeId));
	}

	@PutMapping("/schedule/{scheduleId}")
	public Schedule updateSchedule(@PathVariable Long scheduleId, @Valid @RequestBody Schedule scheduleRequest) {
		return scheduleRepository.findById(scheduleId).map(schedule -> {
			return scheduleRepository.save(scheduleRequest);
		}).orElseThrow(() -> new ResourceNotFoundException("Schedule not found with id " + scheduleId));
	}

	@DeleteMapping("/schedule/{scheduleId}")
	public ResponseEntity<?> deleteSchedule(@PathVariable Long scheduleId) {
		return scheduleRepository.findById(scheduleId).map(schedule -> {
			scheduleRepository.delete(schedule);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Schedule not found with id " + scheduleId));

	}
	
	/**
	 * Gets paged schedules
	 * @return page of schedules
	 */
	@GetMapping("/schedules/paged")
	 public Page<Schedule> getPagedSchedules(
			 @RequestParam("filter") String filter,
			 @RequestParam("ordering") String ordering,
			 @RequestParam("limit") int limit,
			 @RequestParam("offset") int offset
			 ) {
		Pageable pageable = new OffsetBasedPageRequest(offset, limit);
	 	return scheduleRepository.findAll(pageable);
	 }

}
