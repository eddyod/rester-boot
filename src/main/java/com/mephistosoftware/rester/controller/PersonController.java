package com.mephistosoftware.rester.controller;

import com.mephistosoftware.rester.exception.ResourceNotFoundException;
import com.mephistosoftware.rester.model.Person;
import com.mephistosoftware.rester.repository.PersonRepository;
import com.mephistosoftware.rester.security.SecurityConstants;

import org.springframework.beans.factory.annotation.Autowired;
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
	 * This is the non-secured url where an anonymous user can register
	 * 
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

	@GetMapping("/persons/{personId}")
	public Person getPersonById(@PathVariable Long personId) {
		return personRepository.findById(personId)
				.orElseThrow(() -> new ResourceNotFoundException("Location not found with id " + personId));
	}
	
	/**
	 * Gets all persons
	 * @return list of people
	 */
	@GetMapping("/persons")
	public List<Person> getPersons() {
		return personRepository.findAll();
	}

	/**
	 * Gets only employees
	 * @return list of people
	 */
	@GetMapping("/employees")
	public List<Person> getEmployees() {
		return personRepository.findAllEmployees(SecurityConstants.TEACHER);
	}

	@PostMapping("/persons")
	public Person addPerson(@Valid @RequestBody Person person) {
		return personRepository.save(person);
	}

	@PutMapping("/persons/{personId}")
	public Person updatePerson(@PathVariable Long personId, @Valid @RequestBody Person personRequest) {
		return personRepository.findById(personId).map(person -> {
			return personRepository.save(person);
		}).orElseThrow(() -> new ResourceNotFoundException("Person not found with id " + personId));
	}

	@DeleteMapping("/persons/{personId}")
	public ResponseEntity<?> deletePerson(@PathVariable Long personId) {
		return personRepository.findById(personId).map(person -> {
			personRepository.delete(person);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Person not found with id " + personId));

	}

}
