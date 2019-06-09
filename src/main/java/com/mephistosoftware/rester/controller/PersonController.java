package com.mephistosoftware.rester.controller;

import com.mephistosoftware.rester.exception.ResourceNotFoundException;
import com.mephistosoftware.rester.model.Person;
import com.mephistosoftware.rester.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
public class PersonController {
	

	@Autowired
	private PersonRepository personRepository;

	@GetMapping("/persons")
	public List<Person> getPersons() {
		return personRepository.findAll();
	}

	@PostMapping("/persons")
	public Person addPerson(@Valid @RequestBody Person person) {
		return personRepository.save(person);
	}

	@PutMapping("/persons/{personId}")
	public Person updatePerson(@PathVariable Long personId,
			@Valid @RequestBody Person personRequest) {
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
