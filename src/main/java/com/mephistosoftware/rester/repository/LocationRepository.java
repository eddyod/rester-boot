package com.mephistosoftware.rester.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mephistosoftware.rester.model.Location;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
	
    List<Location> findBySiteId(Long siteId);
}
