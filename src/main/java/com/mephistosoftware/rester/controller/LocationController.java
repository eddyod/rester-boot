package com.mephistosoftware.rester.controller;

import com.mephistosoftware.rester.exception.ResourceNotFoundException;
import com.mephistosoftware.rester.model.Location;
import com.mephistosoftware.rester.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import java.util.List;

@RestController
@Validated
public class LocationController {

	@Autowired
	private LocationRepository locationRepository;

	@GetMapping("/locations")
	public List<Location> getLocations() {
		return locationRepository.findAll();
	}

	@GetMapping("/location/{locationId}")
	public Location getLocationsById(@PathVariable @Min(1) Long locationId) {
		return locationRepository.findById(locationId)
				.orElseThrow(() -> new ResourceNotFoundException("Location not found with id " + locationId));
	}

	@PostMapping("/location")
	public Location addLocation(@Valid @RequestBody Location location) {
		return locationRepository.save(location);
	}

	@PutMapping("/location/{locationId}")
	public Location updateLocation(@PathVariable @Min(1) Long locationId, @Valid @RequestBody Location locationRequest) {
		return locationRepository.findById(locationId).map(location -> {
			return locationRepository.save(locationRequest);
		}).orElseThrow(() -> new ResourceNotFoundException("Location not found with id " + locationId));
	}

	@DeleteMapping("/location/{locationId}")
	public ResponseEntity<?> deleteLocation(@PathVariable @Min(1) Long locationId) {
		return locationRepository.findById(locationId).map(location -> {
			locationRepository.delete(location);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Location not found with id " + locationId));

	}
}
