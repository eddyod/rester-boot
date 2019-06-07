package com.mephistosoftware.rester.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mephistosoftware.rester.model.Employee;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
    List<Employee> findBySiteId(Long siteId);
}
