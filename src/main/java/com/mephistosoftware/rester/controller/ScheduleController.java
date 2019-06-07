package com.mephistosoftware.rester.controller;

import com.mephistosoftware.rester.exception.ResourceNotFoundException;
import com.mephistosoftware.rester.model.Schedule;
import com.mephistosoftware.rester.repository.EmployeeRepository;
import com.mephistosoftware.rester.repository.LocationRepository;
import com.mephistosoftware.rester.repository.ScheduleRepository;
import com.mephistosoftware.rester.repository.SiteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sites")
public class ScheduleController {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("/{siteId}/schedules")
    public List<Schedule> getSchedulesBySiteId(@PathVariable Long siteId) {
        return scheduleRepository.findBySiteId(siteId);
    }

    @PostMapping("/{siteId}/schedules")
    public Schedule addScheduleXXX(@PathVariable Long siteId,
                            @Valid @RequestBody Schedule schedule) {
        return siteRepository.findById(siteId)
                .map(site -> {
                    schedule.setSite(site);
                    return scheduleRepository.save(schedule);
                }).orElseThrow(() -> new ResourceNotFoundException("Site not found with id " + siteId));
    }
    
    
    @PostMapping("/{siteId}/{employeeId}/{locationId}/schedules")
    public Schedule addSchedule(
    		@PathVariable Long siteId,  
    		@PathVariable Long employeeId,  
    		@PathVariable Long locationId,  
    		@Valid @RequestBody Schedule schedule) {
    	
    	
        if(!siteRepository.existsById(siteId)) {
            throw new ResourceNotFoundException("Site not found with id " + siteId);
        }
    	
        if(!employeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException("Employee not found with id " + employeeId);
        }
    	
        if(!locationRepository.existsById(locationId)) {
            throw new ResourceNotFoundException("Location not found with id " + locationId);
        }
        
        schedule.setSiteId(siteId);
        schedule.setEmployeeId(employeeId);
        schedule.setLocationId(locationId);
        return scheduleRepository.save(schedule);
    }

    @DeleteMapping("/{siteId}/schedules/{scheduleId}")
    public ResponseEntity<?> deleteSchedule(@PathVariable Long siteId,
                                          @PathVariable Long scheduleId) {
        if(!siteRepository.existsById(siteId)) {
            throw new ResourceNotFoundException("Site not found with id " + siteId);
        }

        return scheduleRepository.findById(scheduleId)
                .map(schedule -> {
                    scheduleRepository.delete(schedule);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Schedule not found with id " + scheduleId));

    }
}
