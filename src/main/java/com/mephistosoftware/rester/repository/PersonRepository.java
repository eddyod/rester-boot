package com.mephistosoftware.rester.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mephistosoftware.rester.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	Person findByEmail(String email);

	@Query(value = "select p from Person p where p.personType = :teacher order by p.lastName, p.firstName")
	List<Person> findAllEmployees(@Param("teacher") Integer teacher);
	
}
