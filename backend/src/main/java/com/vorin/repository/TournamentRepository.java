package com.vorin.repository;

import com.vorin.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    List<Tournament> findByCreatedById(Long userId);
    List<Tournament> findByStatus(Tournament.TournamentStatus status);
}
