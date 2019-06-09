package com.mephistosoftware.rester.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mephistosoftware.rester.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	Person findByEmail(String email);
	
}
