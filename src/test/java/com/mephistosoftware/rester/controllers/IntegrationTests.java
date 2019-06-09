package com.mephistosoftware.rester.controllers;

import com.mephistosoftware.rester.controller.PersonController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ValidationException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTests {

	@Autowired
	PersonController personController;

	@Test
	public void testCreateReadDelete() {

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

		AppUser siteResult = userController.register(user);

		Iterable<AppUser> users = userController.getUsers(null);
		Assertions.assertThat(users).first().hasFieldOrPropertyWithValue("username", "eddyod");

		userController.deleteUser(siteResult.getId());
		Assertions.assertThat(userController.getUsers(null)).isEmpty();
		*/

	}

	@Test(expected = ValidationException.class)
	public void errorHandlingValidationExceptionThrown() {
		personController.getPersons();
	}
}
