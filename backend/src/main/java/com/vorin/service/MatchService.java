package com.vorin.service;

import com.vorin.dto.ScoreUpdateMessage;
import com.vorin.model.Match;
import com.vorin.model.Team;
import com.vorin.repository.MatchRepository;
import com.vorin.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MatchService {
    
    @Autowired
    private MatchRepository matchRepository;
    
    @Autowired
    private TeamRepository teamRepository;
    
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }
    
    public List<Match> getMatchesByDivisionId(Long divisionId) {
        return matchRepository.findByDivisionId(divisionId);
    }
    
    public Match getMatchById(Long id) {
        return matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match not found"));
    }
    
    public Match createMatch(Match match) {
        return matchRepository.save(match);
    }
    
    public Match updateMatch(Long id, Match matchDetails) {
        Match match = getMatchById(id);
        match.setScheduledTime(matchDetails.getScheduledTime());
        match.setVenue(matchDetails.getVenue());
        match.setStatus(matchDetails.getStatus());
        return matchRepository.save(match);
    }
    
    public Match updateScore(Long id, Integer team1Score, Integer team2Score) {
        Match match = getMatchById(id);
        match.setTeam1Score(team1Score);
        match.setTeam2Score(team2Score);
        
        // Update match status
        if (match.getStatus() == Match.MatchStatus.SCHEDULED) {
            match.setStatus(Match.MatchStatus.IN_PROGRESS);
        }
        
        Match savedMatch = matchRepository.save(match);
        
        // Send WebSocket notification
        ScoreUpdateMessage message = new ScoreUpdateMessage(
            savedMatch.getId(),
            savedMatch.getTeam1Score(),
            savedMatch.getTeam2Score(),
            savedMatch.getStatus().toString()
        );
        messagingTemplate.convertAndSend("/topic/scores", message);
        
        return savedMatch;
    }
    
    public Match completeMatch(Long id) {
        Match match = getMatchById(id);
        match.setStatus(Match.MatchStatus.COMPLETED);
        
        // Update team statistics
        Team team1 = match.getTeam1();
        Team team2 = match.getTeam2();
        
        if (match.getTeam1Score() > match.getTeam2Score()) {
            team1.setWins(team1.getWins() + 1);
            team1.setPoints(team1.getPoints() + 3);
            team2.setLosses(team2.getLosses() + 1);
        } else if (match.getTeam1Score() < match.getTeam2Score()) {
            team2.setWins(team2.getWins() + 1);
            team2.setPoints(team2.getPoints() + 3);
            team1.setLosses(team1.getLosses() + 1);
        } else {
            team1.setDraws(team1.getDraws() + 1);
            team1.setPoints(team1.getPoints() + 1);
            team2.setDraws(team2.getDraws() + 1);
            team2.setPoints(team2.getPoints() + 1);
        }
        
        teamRepository.save(team1);
        teamRepository.save(team2);
        
        Match savedMatch = matchRepository.save(match);
        
        // Send WebSocket notification
        ScoreUpdateMessage message = new ScoreUpdateMessage(
            savedMatch.getId(),
            savedMatch.getTeam1Score(),
            savedMatch.getTeam2Score(),
            savedMatch.getStatus().toString()
        );
        messagingTemplate.convertAndSend("/topic/scores", message);
        
        return savedMatch;
    }
    
    public void deleteMatch(Long id) {
        Match match = getMatchById(id);
        matchRepository.delete(match);
    }
}
