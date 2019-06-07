package com.mephistosoftware.rester.controller;

import com.mephistosoftware.rester.exception.ResourceNotFoundException;
import com.mephistosoftware.rester.model.Person;
import com.mephistosoftware.rester.model.Site;
import com.mephistosoftware.rester.repository.AppUserRepository;
import com.mephistosoftware.rester.repository.SiteRepository;
import com.mephistosoftware.rester.security.SecurityConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.Optional;

@RestController
public class UserController {

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private AppUserRepository userRepository;

	@Autowired
	private SiteRepository siteRepository;

	public UserController(AppUserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	/**
	 * This is the non-secured url where an anonymous user can register
	 * 
	 * @param user object of AppUser
	 * @return an Appuser object
	 */
	@PostMapping(SecurityConstants.SIGN_UP_URL)
	public ResponseEntity<Person> register(@RequestBody Person user) {
		if (user.getPassword() != null && user.getEmail() != null) {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			userRepository.save(user);
			return new ResponseEntity<Person>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<Person>(user, HttpStatus.NOT_ACCEPTABLE);
			
		}

	}

	/**
	 * When a user creates a new site, it will add to the site table and also to the
	 * user_site table with the correct user and site. This is for the 2nd page in
	 * the registration process.
	 * 
	 * @param ownerId id of user creating site
	 * @param site    incoming new site
	 * @return returns the site info
	 */
	@PostMapping("/users/{ownerId}/sites")
	public Site addUserSite(@PathVariable Long ownerId, @Valid @RequestBody Site site) {

		return userRepository.findById(ownerId).map(user -> {
			site.setActive(true);
			site.setOwner(user);
			site.getUsers().add(user);
			return siteRepository.save(site);
		}).orElseThrow(() -> new ResourceNotFoundException("Site not found with id " + ownerId));
	}

	@GetMapping()
	public Page<Person> getUsers(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@GetMapping("/{userId}")
	Optional<Person> findById(@PathVariable Long userId) {
		return userRepository.findById(userId);
	}

	@GetMapping("/search")
	Optional<Person> findByQuery(@RequestParam(value = "fname", required = false) String firstName,
			@RequestParam(value = "lname", required = true) String lastName) {
		return userRepository.findByFirstNameOrLastName(firstName, lastName);
	}

	@PutMapping("/{userId}")
	public Person updateUser(@PathVariable Long userId, @Valid @RequestBody Person userRequest) {
		return userRepository.findById(userId).map(user -> {
			user.setFirstName(userRequest.getFirstName());
			user.setLastName(userRequest.getLastName());
			return userRepository.save(user);
		}).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
		return userRepository.findById(userId).map(user -> {
			userRepository.delete(user);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
	}
}
