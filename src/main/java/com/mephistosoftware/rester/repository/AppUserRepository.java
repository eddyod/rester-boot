package com.mephistosoftware.rester.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mephistosoftware.rester.model.Person;

@Repository
public interface AppUserRepository extends JpaRepository<Person, Long> {
	
	@Query("select u from AppUser u where u.email = :username")
	Person findByUsername(@Param("username") String username);
	
	@Query("select u from AppUser u where u.email = :email")
	Person findByEmail(@Param("email") String email);

	Optional<Person> findByFirstNameOrLastName(String firstName, String lastName);
	

}
