package com.mephistosoftware.rester.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.mephistosoftware.rester.controller.PersonController;
import com.mephistosoftware.rester.repository.PersonRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class StandaloneControllerTests {

	@MockBean
	PersonRepository personRepository;

	@Autowired
	MockMvc mockMvc;

	@Test
	public void testCreateReadDelete() throws Exception {
		
		/*
		Set<GrantedAuthority> authorities = new HashSet<>();
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
		List<AppUser> users = Arrays.asList(user);
		Mockito.when(appUserRepository.findAll()).thenReturn(users);

		mockMvc.perform(get("/users")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].username", Matchers.is("eddyod")));
		*/
	}

}
