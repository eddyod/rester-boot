package com.mephistosoftware.rester.controller;

import com.mephistosoftware.rester.exception.ResourceNotFoundException;
import com.mephistosoftware.rester.model.Location;
import com.mephistosoftware.rester.repository.LocationRepository;
import com.mephistosoftware.rester.repository.SiteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sites")
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private SiteRepository siteRepository;

    @GetMapping("/{siteId}/locations")
    public List<Location> getLocationsBySiteId(@PathVariable Long siteId) {
        return locationRepository.findBySiteId(siteId);
    }

    @PostMapping("/{siteId}/locations")
    public Location addLocation(@PathVariable Long siteId,
                            @Valid @RequestBody Location location) {
        return siteRepository.findById(siteId)
                .map(site -> {
                    location.setSite(site);
                    return locationRepository.save(location);
                }).orElseThrow(() -> new ResourceNotFoundException("Site not found with id " + siteId));
    }

    @PutMapping("/{siteId}/locations/{locationId}")
    public Location updateLocation(@PathVariable Long siteId,
                               @PathVariable Long locationId,
                               @Valid @RequestBody Location locationRequest) {
        if(!siteRepository.existsById(siteId)) {
            throw new ResourceNotFoundException("Site not found with id " + siteId);
        }

        return locationRepository.findById(locationId)
                .map(location -> {
                    location.setName(locationRequest.getName());
                    return locationRepository.save(location);
                }).orElseThrow(() -> new ResourceNotFoundException("Location not found with id " + locationId));
    }

    @DeleteMapping("/{siteId}/locations/{locationId}")
    public ResponseEntity<?> deleteLocation(@PathVariable Long siteId,
                                          @PathVariable Long locationId) {
        if(!siteRepository.existsById(siteId)) {
            throw new ResourceNotFoundException("Site not found with id " + siteId);
        }

        return locationRepository.findById(locationId)
                .map(location -> {
                    locationRepository.delete(location);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Location not found with id " + locationId));

    }
}
