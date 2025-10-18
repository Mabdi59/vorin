package com.vorin.controller;

import com.vorin.model.Division;
import com.vorin.model.Match;
import com.vorin.scheduler.SchedulingService;
import com.vorin.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DivisionController {
    
    @Autowired
    private DivisionService divisionService;
    
    @Autowired
    private SchedulingService schedulingService;
    
    @GetMapping("/divisions")
    public ResponseEntity<List<Division>> getAllDivisions() {
        return ResponseEntity.ok(divisionService.getAllDivisions());
    }
    
    @GetMapping("/tournaments/{tournamentId}/divisions")
    public ResponseEntity<List<Division>> getDivisionsByTournamentId(@PathVariable Long tournamentId) {
        return ResponseEntity.ok(divisionService.getDivisionsByTournamentId(tournamentId));
    }
    
    @GetMapping("/divisions/{id}")
    public ResponseEntity<Division> getDivisionById(@PathVariable Long id) {
        return ResponseEntity.ok(divisionService.getDivisionById(id));
    }
    
    @PostMapping("/tournaments/{tournamentId}/divisions")
    public ResponseEntity<Division> createDivision(@PathVariable Long tournamentId, @RequestBody Division division) {
        return ResponseEntity.ok(divisionService.createDivision(tournamentId, division));
    }
    
    @PutMapping("/divisions/{id}")
    public ResponseEntity<Division> updateDivision(@PathVariable Long id, @RequestBody Division division) {
        return ResponseEntity.ok(divisionService.updateDivision(id, division));
    }
    
    @DeleteMapping("/divisions/{id}")
    public ResponseEntity<Void> deleteDivision(@PathVariable Long id) {
        divisionService.deleteDivision(id);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/divisions/{id}/generate-schedule")
    public ResponseEntity<List<Match>> generateSchedule(@PathVariable Long id, @RequestParam String startTime) {
        Division division = divisionService.getDivisionById(id);
        LocalDateTime start = LocalDateTime.parse(startTime);
        List<Match> matches = schedulingService.generateSchedule(division, start);
        return ResponseEntity.ok(matches);
    }
}
