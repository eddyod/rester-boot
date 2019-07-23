package com.mephistosoftware.rester.controller;

import com.mephistosoftware.rester.exception.ResourceNotFoundException;
import com.mephistosoftware.rester.model.Location;
import com.mephistosoftware.rester.model.Person;
import com.mephistosoftware.rester.repository.LocationRepository;
import com.mephistosoftware.rester.repository.OffsetBasedPageRequest;
import com.mephistosoftware.rester.repository.PersonRepository;
import com.mephistosoftware.rester.security.SecurityConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.persistence.PersistenceException;
import javax.validation.Valid;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// @CrossOrigin( origins = "*" )
@RestController
public class PersonController {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private LocationRepository locationRepository;

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public PersonController(PersonRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.personRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}	
	
	/**
	 * This is the non-secured url where an anonymous user can register *
	 * 
	 * @param user object of Person
	 * @return an Person object
	 */
	@PostMapping(SecurityConstants.SIGN_UP_URL)
	public ResponseEntity<Person> registerWithResponse(@Valid @RequestBody Person person) {
		if (person.getPassword() != null && person.getEmail() != null) {
			Person testPerson = personRepository.findByEmail(person.getEmail());
			if (testPerson != null && testPerson.getId() != null) {
				return new ResponseEntity<Person>(person, HttpStatus.BAD_REQUEST);
			}
			person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
			personRepository.save(person);
			return new ResponseEntity<Person>(person, HttpStatus.OK);
		} else {
			return new ResponseEntity<Person>(person, HttpStatus.NOT_ACCEPTABLE);
		}

	}

	@GetMapping("/person/{id}")
	public Person getPersonById(@PathVariable Long id) {
		return personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Person not found with id " + id));
	}

	/**
	 * Gets all persons
	 * 
	 * @return list of people
	 */
	@GetMapping("/persons")
	public List<Person> getPersons() {
		return personRepository.findAll(Sort.by("lastName"));
	}

	/**
	 * Gets only employees
	 * 
	 * @return list of people
	 */
	@GetMapping("/employees")
	public List<Person> getEmployees() {
		return personRepository.findAllEmployees(SecurityConstants.TEACHER);
	}

	/**
	 * Gets only employees
	 * 
	 * @return list of people
	 */
	@GetMapping("/employees/paged")
	public Page<Person> getPagedEmployees(@RequestParam("filter") String filter,
			@RequestParam("ordering") String ordering, @RequestParam("limit") int limit,
			@RequestParam("offset") int offset) {
		Pageable pageable = new OffsetBasedPageRequest(offset, limit);
		return personRepository.findPagedEmployees(SecurityConstants.TEACHER, pageable);
	}

	/**
	 * Add employee with specific employee settings
	 * 
	 * @param person json object of person
	 * @return the person object
	 */
	@PostMapping("/employee")
	public ResponseEntity<Person> addEmployee(@Valid @RequestBody Person person) {
		person.setActive(true);
		person.setPersonType(SecurityConstants.TEACHER);
		try {
			personRepository.save(person);
		} catch (PersistenceException pe) {
			System.out.println("could not persist person");
			return new ResponseEntity<Person>(person, HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}

	@PostMapping("/person")
	public Person addPerson(@Valid @RequestBody Person person) {
		return personRepository.save(person);
	}

	@PutMapping("/person/{id}")
	public Person updatePerson(@PathVariable(value = "id") Long id, @Valid @RequestBody Person personRequest) {
		return personRepository.findById(id).map(person -> {
			person.setFirstName(personRequest.getFirstName());
			person.setLastName(personRequest.getLastName());
			person.setPhone(personRequest.getPhone());
			person.setEmail(personRequest.getEmail());
			person.setAddress(personRequest.getAddress());
			person.setActive(personRequest.getActive());
			return personRepository.save(person);
		}).orElseThrow(() -> new ResourceNotFoundException("Person not found with id " + id));
	}

	@DeleteMapping("/person/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable Long id) {
		return personRepository.findById(id).map(person -> {
			personRepository.delete(person);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Person not found with id " + id));

	}
	

	@PostMapping("/employee/{employeeId}/{locationId}")
	public Person attachEmployeeSchool(@PathVariable Long employeeId, @PathVariable Long locationId) {
		Set<Location> schools = new HashSet<>();
		return personRepository.findById(employeeId).map(employee -> {
			return locationRepository.findById(locationId).map(location -> {
				schools.add(location);
				employee.setSchools(schools);
				return personRepository.save(employee);
			}).orElseThrow(() -> new ResourceNotFoundException("Location not found with id " + locationId));
		}).orElseThrow(() -> new ResourceNotFoundException("Person not found with id " + employeeId));
	}


}
