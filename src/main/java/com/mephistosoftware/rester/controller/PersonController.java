package com.mephistosoftware.rester.controller;

import com.mephistosoftware.rester.exception.ResourceNotFoundException;
import com.mephistosoftware.rester.model.Person;
import com.mephistosoftware.rester.repository.OffsetBasedPageRequest;
import com.mephistosoftware.rester.repository.PersonRepository;
import com.mephistosoftware.rester.security.SecurityConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
public class PersonController {

	@Autowired
	private PersonRepository personRepository;

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public PersonController(PersonRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.personRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}


	/**
	 * This is the non-secured url where an anonymous user can register	 * 
	 * @param user object of Person
	 * @return an Person object
	 */
	@PostMapping(SecurityConstants.SIGN_UP_URL)
	public ResponseEntity<Person> registerWithResponse(@RequestBody Person person) {
		if (person.getPassword() != null && person.getEmail() != null) {
			person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
			personRepository.save(person);
			return new ResponseEntity<Person>(person, HttpStatus.OK);
		} else {
			return new ResponseEntity<Person>(person, HttpStatus.NOT_ACCEPTABLE);
			
		}

	}

	@GetMapping("/persons/{id}")
	public Person getPersonById(@PathVariable Long id) {
		return personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Location not found with id " + id));
	}
	
	/**
	 * Gets all persons
	 * @return list of people
	 */
	@GetMapping("/persons")
	public List<Person> getPersons() {
		return personRepository.findAll(Sort.by("lastName"));
	}

	/**
	 * Gets only employees
	 * @return list of people
	 */
	@GetMapping("/persons/employees/search")
	public Page<Person> searchEmployees(@RequestParam("ordering") String ordering, @RequestParam("limit") int limit, @RequestParam("offset") int offset) {
		Pageable pageable = new OffsetBasedPageRequest(offset, limit, ordering);
		// Pageable pageable = PageRequest.of(offset, limit);
		return personRepository.findAllEmployees(SecurityConstants.TEACHER, pageable);
	}

	/**
	 * Gets only employees
	 * @return list of people
	 */
	// @GetMapping("/employees")
	// public Page<Person> getEmployees() {
	// 	return personRepository.findAllEmployees(SecurityConstants.TEACHER);
	// }

	/**
	 * Add employee with specific employee settings
	 * @param person json object of person
	 * @return the person object
	 */
	@PostMapping("/employee")
	public Person addEmployee(@Valid @RequestBody Person person) {
		person.setActive(true);
		person.setPersonType(SecurityConstants.TEACHER);
		return personRepository.save(person);
	}

	@PostMapping("/persons")
	public Person addPerson(@Valid @RequestBody Person person) {
		return personRepository.save(person);
	}

	@PutMapping("/persons/{id}")
	public Person updatePerson(@PathVariable Long id, @Valid @RequestBody Person personRequest) {
		return personRepository.findById(id).map(person -> {
			return personRepository.save(person);
		}).orElseThrow(() -> new ResourceNotFoundException("Person not found with id " + id));
	}

	@DeleteMapping("/persons/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable Long id) {
		return personRepository.findById(id).map(person -> {
			personRepository.delete(person);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Person not found with id " + id));

	}

}
