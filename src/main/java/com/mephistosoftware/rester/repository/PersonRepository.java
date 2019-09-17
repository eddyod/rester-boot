package com.mephistosoftware.rester.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mephistosoftware.rester.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	Person findByEmail(String email);

	@Query(value = "select p from Person p where p.personType = :personType order by p.lastName, p.firstName")
	List<Person> findAllEmployees(@Param("personType") Integer personType);
	
	@Query(value = "select p from Person p where p.personType = :teacher order by p.lastName, p.firstName")
	Page<Person> findPagedEmployees(@Param("teacher") Integer teacher, Pageable pageable);

	// @Query(value = "select p.schools from Person p where p.id = :id")
	// Set<Location> getSchoolsByEmployeeId(@Param("id") Long id);
	
}
