package com.vorin.service;

import com.vorin.model.Division;
import com.vorin.model.Team;
import com.vorin.repository.DivisionRepository;
import com.vorin.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeamService {
    
    @Autowired
    private TeamRepository teamRepository;
    
    @Autowired
    private DivisionRepository divisionRepository;
    
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }
    
    public List<Team> getTeamsByDivisionId(Long divisionId) {
        return teamRepository.findByDivisionId(divisionId);
    }
    
    public Team getTeamById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found"));
    }
    
    public Team createTeam(Long divisionId, Team team) {
        Division division = divisionRepository.findById(divisionId)
                .orElseThrow(() -> new RuntimeException("Division not found"));
        
        team.setDivision(division);
        return teamRepository.save(team);
    }
    
    public Team updateTeam(Long id, Team teamDetails) {
        Team team = getTeamById(id);
        team.setName(teamDetails.getName());
        team.setDescription(teamDetails.getDescription());
        return teamRepository.save(team);
    }
    
    public void deleteTeam(Long id) {
        Team team = getTeamById(id);
        teamRepository.delete(team);
    }
}
