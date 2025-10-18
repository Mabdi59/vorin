package com.vorin.service;

import com.vorin.model.Venue;
import com.vorin.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VenueService {
    
    @Autowired
    private VenueRepository venueRepository;
    
    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }
    
    public Venue getVenueById(Long id) {
        return venueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venue not found"));
    }
    
    public Venue createVenue(Venue venue) {
        return venueRepository.save(venue);
    }
    
    public Venue updateVenue(Long id, Venue venueDetails) {
        Venue venue = getVenueById(id);
        venue.setName(venueDetails.getName());
        venue.setAddress(venueDetails.getAddress());
        venue.setCapacity(venueDetails.getCapacity());
        venue.setFacilities(venueDetails.getFacilities());
        return venueRepository.save(venue);
    }
    
    public void deleteVenue(Long id) {
        Venue venue = getVenueById(id);
        venueRepository.delete(venue);
    }
}
