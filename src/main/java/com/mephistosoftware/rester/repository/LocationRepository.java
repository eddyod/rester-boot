package com.mephistosoftware.rester.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mephistosoftware.rester.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
	
}
