package com.vorin.service;

import com.vorin.model.Tournament;
import com.vorin.model.User;
import com.vorin.repository.TournamentRepository;
import com.vorin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TournamentService {
    
    @Autowired
    private TournamentRepository tournamentRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }
    
    public Tournament getTournamentById(Long id) {
        return tournamentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tournament not found"));
    }
    
    public Tournament createTournament(Tournament tournament) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        tournament.setCreatedBy(user);
        return tournamentRepository.save(tournament);
    }
    
    public Tournament updateTournament(Long id, Tournament tournamentDetails) {
        Tournament tournament = getTournamentById(id);
        tournament.setName(tournamentDetails.getName());
        tournament.setDescription(tournamentDetails.getDescription());
        tournament.setStartDate(tournamentDetails.getStartDate());
        tournament.setEndDate(tournamentDetails.getEndDate());
        tournament.setStatus(tournamentDetails.getStatus());
        return tournamentRepository.save(tournament);
    }
    
    public void deleteTournament(Long id) {
        Tournament tournament = getTournamentById(id);
        tournamentRepository.delete(tournament);
    }
}
