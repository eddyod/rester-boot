package com.mephistosoftware.rester.controller;

import com.mephistosoftware.rester.exception.ResourceNotFoundException;
import com.mephistosoftware.rester.model.Employee;
import com.mephistosoftware.rester.repository.EmployeeRepository;
import com.mephistosoftware.rester.repository.SiteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sites")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SiteRepository siteRepository;

    @GetMapping("/sites/{siteId}/employees")
    public List<Employee> getEmployeesBySiteId(@PathVariable Long siteId) {
        return employeeRepository.findBySiteId(siteId);
    }

    @PostMapping("/{siteId}/employees")
    public Employee addEmployee(@PathVariable Long siteId,
                            @Valid @RequestBody Employee employee) {
        return siteRepository.findById(siteId)
                .map(site -> {
                    employee.setSite(site);
                    return employeeRepository.save(employee);
                }).orElseThrow(() -> new ResourceNotFoundException("Site not found with id " + siteId));
    }

    @PutMapping("/{siteId}/employees/{employeeId}")
    public Employee updateEmployee(@PathVariable Long siteId,
                               @PathVariable Long employeeId,
                               @Valid @RequestBody Employee employeeRequest) {
        if(!siteRepository.existsById(siteId)) {
            throw new ResourceNotFoundException("Site not found with id " + siteId);
        }

        return employeeRepository.findById(employeeId)
                .map(employee -> {
                    employee.setName(employeeRequest.getName());
                    return employeeRepository.save(employee);
                }).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + employeeId));
    }

    @DeleteMapping("/{siteId}/employees/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long siteId,
                                          @PathVariable Long employeeId) {
        if(!siteRepository.existsById(siteId)) {
            throw new ResourceNotFoundException("Site not found with id " + siteId);
        }

        return employeeRepository.findById(employeeId)
                .map(employee -> {
                    employeeRepository.delete(employee);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + employeeId));

    }
}
