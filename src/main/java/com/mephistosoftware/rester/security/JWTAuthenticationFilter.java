package com.mephistosoftware.rester.security;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import com.mephistosoftware.rester.model.Location;
import com.mephistosoftware.rester.model.Person;
import com.mephistosoftware.rester.repository.PersonRepository;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.mephistosoftware.rester.security.SecurityConstants.EXPIRATION_TIME;
import static com.mephistosoftware.rester.security.SecurityConstants.SECRET;
import static com.mephistosoftware.rester.security.SecurityConstants.HEADER_STRING;
import static com.mephistosoftware.rester.security.SecurityConstants.TOKEN_PREFIX;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private PersonRepository personRepository;
	
	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {
			Person creds = new ObjectMapper().readValue(req.getInputStream(), Person.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getEmail(),
					creds.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private String buildToken(String token, String email) throws JsonProcessingException {
		Person person;
		String returnToken = "";
		ObjectMapper mapper = new ObjectMapper();
		if (personRepository != null) {
			person = personRepository.findByEmail(email);
			person.setToken(token);
			// Set<Location> schools = personRepository.getSchoolsByEmployeeId(person.getId());
			// person.setSchools(schools);
			returnToken = mapper.writeValueAsString(person);
		} else {
			System.out.println("repo IS NULL ");			
		}
		return returnToken;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		String email = ((User) auth.getPrincipal()).getUsername();
		
		String token = JWT.create().withSubject(email)
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).sign(HMAC512(SECRET.getBytes()));
		response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		ServletContext servletContext = req.getServletContext();
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        personRepository = webApplicationContext.getBean(PersonRepository.class);
        
		String returnToken = buildToken(token, email);
		response.getWriter().write(returnToken);
		
		
		
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse res,
			AuthenticationException failed) throws IOException, ServletException {
		// super.unsuccessfulAuthentication(req, res, failed);
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write("{\"non_field_errors\":[\"Unable to log in with provided credentials.\"]}");
	}
}