package com.vorin.scheduler;

import com.vorin.model.Division;
import com.vorin.model.Match;
import com.vorin.model.Team;
import com.vorin.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SchedulingService {
    
    @Autowired
    private MatchRepository matchRepository;
    
    public List<Match> generateSchedule(Division division, LocalDateTime startTime) {
        List<Match> matches = new ArrayList<>();
        
        switch (division.getBracketType()) {
            case ROUND_ROBIN:
                matches = generateRoundRobinSchedule(division, startTime);
                break;
            case SINGLE_ELIMINATION:
                matches = generateSingleEliminationSchedule(division, startTime);
                break;
            case DOUBLE_ELIMINATION:
                matches = generateDoubleEliminationSchedule(division, startTime);
                break;
        }
        
        return matchRepository.saveAll(matches);
    }
    
    private List<Match> generateRoundRobinSchedule(Division division, LocalDateTime startTime) {
        List<Match> matches = new ArrayList<>();
        List<Team> teams = division.getTeams();
        
        if (teams.size() < 2) {
            return matches;
        }
        
        int matchCounter = 0;
        
        // Generate all possible matches between teams
        for (int i = 0; i < teams.size(); i++) {
            for (int j = i + 1; j < teams.size(); j++) {
                Match match = new Match();
                match.setDivision(division);
                match.setTeam1(teams.get(i));
                match.setTeam2(teams.get(j));
                match.setScheduledTime(startTime.plusDays(matchCounter));
                match.setRoundNumber(1);
                match.setStatus(Match.MatchStatus.SCHEDULED);
                matches.add(match);
                matchCounter++;
            }
        }
        
        return matches;
    }
    
    private List<Match> generateSingleEliminationSchedule(Division division, LocalDateTime startTime) {
        List<Match> matches = new ArrayList<>();
        List<Team> teams = new ArrayList<>(division.getTeams());
        
        if (teams.size() < 2) {
            return matches;
        }
        
        int roundNumber = 1;
        int matchCounter = 0;
        
        // Create initial round matches
        for (int i = 0; i < teams.size(); i += 2) {
            if (i + 1 < teams.size()) {
                Match match = new Match();
                match.setDivision(division);
                match.setTeam1(teams.get(i));
                match.setTeam2(teams.get(i + 1));
                match.setScheduledTime(startTime.plusDays(matchCounter));
                match.setRoundNumber(roundNumber);
                match.setBracketPosition(i / 2);
                match.setStatus(Match.MatchStatus.SCHEDULED);
                matches.add(match);
                matchCounter++;
            }
        }
        
        // Generate placeholder matches for subsequent rounds
        int teamsRemaining = teams.size() / 2;
        while (teamsRemaining > 1) {
            roundNumber++;
            for (int i = 0; i < teamsRemaining; i += 2) {
                Match match = new Match();
                match.setDivision(division);
                // Winners will be determined later
                match.setScheduledTime(startTime.plusDays(matchCounter * 2));
                match.setRoundNumber(roundNumber);
                match.setBracketPosition(i / 2);
                match.setStatus(Match.MatchStatus.SCHEDULED);
                matches.add(match);
                matchCounter++;
            }
            teamsRemaining /= 2;
        }
        
        return matches;
    }
    
    private List<Match> generateDoubleEliminationSchedule(Division division, LocalDateTime startTime) {
        // Similar to single elimination but with a losers bracket
        List<Match> matches = generateSingleEliminationSchedule(division, startTime);
        
        // This is a simplified version - full double elimination would require more complex logic
        // to track losers bracket and finals
        
        return matches;
    }
}
