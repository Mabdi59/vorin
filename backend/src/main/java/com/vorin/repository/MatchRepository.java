package com.vorin.repository;

import com.vorin.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByDivisionId(Long divisionId);
    List<Match> findByStatus(Match.MatchStatus status);
    List<Match> findByDivisionIdAndRoundNumber(Long divisionId, Integer roundNumber);
}
