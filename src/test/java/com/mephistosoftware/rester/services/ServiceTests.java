package com.mephistosoftware.rester.services;

import static java.util.Collections.emptyList;

import java.util.HashSet;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;

import com.mephistosoftware.rester.model.Person;
import com.mephistosoftware.rester.repository.AppUserRepository;

@RunWith(SpringRunner.class)

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ServiceTests {

	@Autowired
	AppUserRepository appUserRepository;

	@Test
	public void testCreateReadDelete() {
		Set<GrantedAuthority> authorities = new HashSet<>();

		/*
		AppUser user = new AppUser();

		user.setUsername("eddyod");
		user.setFirstName("1234567");
		user.setLastName("1234567");
		user.setEmail("joe@blow.com");
		user.setPhone("1234567");
		user.setSuperUser(true);
		user.setStaff(true);
		user.setActive(true);
		user.setPassword("1234567");

		appUserRepository.save(user);
		*/
		Iterable<Person> users = appUserRepository.findAll();
		Assertions.assertThat(users).extracting(Person::getEmail).containsOnly("eddyod");
		
		appUserRepository.deleteAll();
		Assertions.assertThat(appUserRepository.findAll()).isEmpty();
	}
}
