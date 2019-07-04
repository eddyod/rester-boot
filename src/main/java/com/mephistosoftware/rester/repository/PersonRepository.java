package com.mephistosoftware.rester.repository;

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

	@Query(value = "select p from Person p where p.personType = :teacher")
	Page<Person> findAllEmployees(@Param("teacher") Integer teacher, Pageable pageable);
	
}
