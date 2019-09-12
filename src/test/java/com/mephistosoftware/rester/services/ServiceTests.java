package com.mephistosoftware.rester.services;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mephistosoftware.rester.model.Person;
import com.mephistosoftware.rester.repository.PersonRepository;

@RunWith(SpringRunner.class)

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ServiceTests {

	@Autowired
	PersonRepository personRepository;

	/*
	@Test
	public void testCreateReadDelete() {
		
		String email = "jasonodonnell@ucsd.edu";

		Person user = new Person();
		user.setFirstName("Jason");
		user.setLastName("OD");
		user.setEmail(email);
		user.setPhone("123-4567");
		user.setPassword("j1234567");

		personRepository.save(user);
		
		Iterable<Person> users = personRepository.findAll();
		Assertions.assertThat(users).extracting(Person::getEmail).contains(email);
		
		personRepository.deleteAll();
		Assertions.assertThat(personRepository.findAll()).isEmpty();
	}
	*/
}
