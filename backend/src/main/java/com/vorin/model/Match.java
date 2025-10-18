package com.vorin.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "matches")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "division_id", nullable = false)
    private Division division;
    
    @ManyToOne
    @JoinColumn(name = "team1_id", nullable = false)
    private Team team1;
    
    @ManyToOne
    @JoinColumn(name = "team2_id", nullable = false)
    private Team team2;
    
    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;
    
    @Column(name = "scheduled_time")
    private LocalDateTime scheduledTime;
    
    @Column(name = "team1_score")
    private Integer team1Score = 0;
    
    @Column(name = "team2_score")
    private Integer team2Score = 0;
    
    @Enumerated(EnumType.STRING)
    private MatchStatus status = MatchStatus.SCHEDULED;
    
    @Column(name = "round_number")
    private Integer roundNumber;
    
    @Column(name = "bracket_position")
    private Integer bracketPosition;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    public enum MatchStatus {
        SCHEDULED, IN_PROGRESS, COMPLETED, CANCELLED
    }
}
