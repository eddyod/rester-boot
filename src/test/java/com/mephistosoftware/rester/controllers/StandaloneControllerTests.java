package com.mephistosoftware.rester.controllers;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.mephistosoftware.rester.controller.UserController;
import com.mephistosoftware.rester.model.Person;
import com.mephistosoftware.rester.repository.AppUserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.emptyList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class StandaloneControllerTests {

	@MockBean
	AppUserRepository appUserRepository;

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
