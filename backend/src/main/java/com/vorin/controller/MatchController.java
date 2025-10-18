package com.vorin.controller;

import com.vorin.model.Match;
import com.vorin.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MatchController {
    
    @Autowired
    private MatchService matchService;
    
    @GetMapping("/matches")
    public ResponseEntity<List<Match>> getAllMatches() {
        return ResponseEntity.ok(matchService.getAllMatches());
    }
    
    @GetMapping("/divisions/{divisionId}/matches")
    public ResponseEntity<List<Match>> getMatchesByDivisionId(@PathVariable Long divisionId) {
        return ResponseEntity.ok(matchService.getMatchesByDivisionId(divisionId));
    }
    
    @GetMapping("/matches/{id}")
    public ResponseEntity<Match> getMatchById(@PathVariable Long id) {
        return ResponseEntity.ok(matchService.getMatchById(id));
    }
    
    @PostMapping("/matches")
    public ResponseEntity<Match> createMatch(@RequestBody Match match) {
        return ResponseEntity.ok(matchService.createMatch(match));
    }
    
    @PutMapping("/matches/{id}")
    public ResponseEntity<Match> updateMatch(@PathVariable Long id, @RequestBody Match match) {
        return ResponseEntity.ok(matchService.updateMatch(id, match));
    }
    
    @PutMapping("/matches/{id}/score")
    public ResponseEntity<Match> updateScore(@PathVariable Long id, @RequestBody Map<String, Integer> scores) {
        Integer team1Score = scores.get("team1Score");
        Integer team2Score = scores.get("team2Score");
        return ResponseEntity.ok(matchService.updateScore(id, team1Score, team2Score));
    }
    
    @PutMapping("/matches/{id}/complete")
    public ResponseEntity<Match> completeMatch(@PathVariable Long id) {
        return ResponseEntity.ok(matchService.completeMatch(id));
    }
    
    @DeleteMapping("/matches/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        matchService.deleteMatch(id);
        return ResponseEntity.ok().build();
    }
}
