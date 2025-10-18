package com.vorin.controller;

import com.vorin.model.Team;
import com.vorin.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TeamController {
    
    @Autowired
    private TeamService teamService;
    
    @GetMapping("/teams")
    public ResponseEntity<List<Team>> getAllTeams() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }
    
    @GetMapping("/divisions/{divisionId}/teams")
    public ResponseEntity<List<Team>> getTeamsByDivisionId(@PathVariable Long divisionId) {
        return ResponseEntity.ok(teamService.getTeamsByDivisionId(divisionId));
    }
    
    @GetMapping("/teams/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.getTeamById(id));
    }
    
    @PostMapping("/divisions/{divisionId}/teams")
    public ResponseEntity<Team> createTeam(@PathVariable Long divisionId, @RequestBody Team team) {
        return ResponseEntity.ok(teamService.createTeam(divisionId, team));
    }
    
    @PutMapping("/teams/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @RequestBody Team team) {
        return ResponseEntity.ok(teamService.updateTeam(id, team));
    }
    
    @DeleteMapping("/teams/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return ResponseEntity.ok().build();
    }
}
