package com.vorin.service;

import com.vorin.model.Division;
import com.vorin.model.Tournament;
import com.vorin.repository.DivisionRepository;
import com.vorin.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DivisionService {
    
    @Autowired
    private DivisionRepository divisionRepository;
    
    @Autowired
    private TournamentRepository tournamentRepository;
    
    public List<Division> getAllDivisions() {
        return divisionRepository.findAll();
    }
    
    public List<Division> getDivisionsByTournamentId(Long tournamentId) {
        return divisionRepository.findByTournamentId(tournamentId);
    }
    
    public Division getDivisionById(Long id) {
        return divisionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Division not found"));
    }
    
    public Division createDivision(Long tournamentId, Division division) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new RuntimeException("Tournament not found"));
        
        division.setTournament(tournament);
        return divisionRepository.save(division);
    }
    
    public Division updateDivision(Long id, Division divisionDetails) {
        Division division = getDivisionById(id);
        division.setName(divisionDetails.getName());
        division.setDescription(divisionDetails.getDescription());
        division.setBracketType(divisionDetails.getBracketType());
        return divisionRepository.save(division);
    }
    
    public void deleteDivision(Long id) {
        Division division = getDivisionById(id);
        divisionRepository.delete(division);
    }
}
