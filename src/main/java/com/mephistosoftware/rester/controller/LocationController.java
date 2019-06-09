package com.mephistosoftware.rester.controller;

import com.mephistosoftware.rester.exception.ResourceNotFoundException;
import com.mephistosoftware.rester.model.Location;
import com.mephistosoftware.rester.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
public class LocationController {

	@Autowired
	private LocationRepository locationRepository;

	@GetMapping("/locations")
	public List<Location> getLocations() {
		return locationRepository.findAll();
	}

	@PostMapping("/locations")
	public Location addLocation(@Valid @RequestBody Location location) {
		return locationRepository.save(location);
	}

	@PutMapping("/locations/{locationId}")
	public Location updateLocation(@PathVariable Long locationId,
			@Valid @RequestBody Location locationRequest) {
		return locationRepository.findById(locationId).map(location -> {
			location.setName(locationRequest.getName());
			return locationRepository.save(location);
		}).orElseThrow(() -> new ResourceNotFoundException("Location not found with id " + locationId));
	}

	@DeleteMapping("/locations/{locationId}")
	public ResponseEntity<?> deleteLocation(@PathVariable Long locationId) {
		return locationRepository.findById(locationId).map(location -> {
			locationRepository.delete(location);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Location not found with id " + locationId));

	}
}
