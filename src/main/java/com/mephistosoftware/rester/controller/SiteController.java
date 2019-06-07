package com.mephistosoftware.rester.controller;

import com.mephistosoftware.rester.exception.ResourceNotFoundException;
import com.mephistosoftware.rester.model.Site;
import com.mephistosoftware.rester.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@RestController
@RequestMapping("/sites")
public class SiteController {

    @Autowired
    private SiteRepository siteRepository;
    


    @GetMapping()
    public Page<Site> getSites(Pageable pageable) {
        return siteRepository.findAll(pageable);
    }

    @GetMapping("/list")
    public List<Site> getSiteList() {
        return siteRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Optional<Site> get(@PathVariable("id") long id) {
    	return siteRepository.findById(id);
    }

    @PutMapping("/{siteId}")
    public Site updateSite(@PathVariable Long siteId,
                                   @Valid @RequestBody Site siteRequest) {
        return siteRepository.findById(siteId)
                .map(site -> {
                    site.setName(siteRequest.getName());
                    site.setAddress(siteRequest.getAddress());
                    return siteRepository.save(site);
                }).orElseThrow(() -> new ResourceNotFoundException("Site not found with id " + siteId));
    }


    @DeleteMapping("/{siteId}")
    public ResponseEntity<?> deleteSite(@PathVariable Long siteId) {
        return siteRepository.findById(siteId)
                .map(site -> {
                    siteRepository.delete(site);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Site not found with id " + siteId));
    }
}
