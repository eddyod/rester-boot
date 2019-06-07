package com.mephistosoftware.rester.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mephistosoftware.rester.model.Person;
import com.mephistosoftware.rester.repository.AppUserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AppUserRepository userRepository;

	@Autowired
	public UserDetailsServiceImpl(AppUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Person appUser = userRepository.findByEmail(email);
		if (appUser == null) {
			throw new UsernameNotFoundException(email);
		}
		// return new User(user.getUsername(), user.getPassword(),
		// Collections.emptyList());
		return buildUserFromUserEntity(appUser);
		/*
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		
		return new AppUser(domainUser.getUsername(), domainUser.getPassword(), enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, getAuthorities(domainUser.getRole().getRole()));
		*/
	}

	/**
	 * Retrieves a collection of {@link GrantedAuthority} based on a numerical role
	 * 
	 * @param role the numerical role
	 * @return a collection of {@link GrantedAuthority
	 */
	public Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
		return authList;
	}

	/**
	 * Converts a numerical role to an equivalent list of roles
	 * 
	 * @param role the numerical role
	 * @return list of roles as as a list of {@link String}
	 */
	public List<String> getRoles(Integer role) {
		List<String> roles = new ArrayList<String>();

		if (role.intValue() == 1) {
			roles.add("ROLE_USER");
			roles.add("ROLE_ADMIN");

		} else if (role.intValue() == 2) {
			roles.add("ROLE_USER");
		}
		roles.add("ROLE_USER");

		return roles;
	}

	/**
	 * Wraps {@link String} roles to {@link SimpleGrantedAuthority} objects
	 * 
	 * @param roles {@link String} of roles
	 * @return list of granted authorities
	 */
	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

	private User buildUserFromUserEntity(Person userEntity) {
		// convert model user to spring security user
		String email = userEntity.getEmail();
		String password = userEntity.getPassword();
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		// List<GrantedAuthority> authorities = new ArrayList<>();
		// authorities.add(new SimpleGrantedAuthority("role"));

		User springUser = new User(email, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, new ArrayList<>());
		return springUser;
	}

}