package com.vorin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreUpdateMessage {
    private Long matchId;
    private Integer team1Score;
    private Integer team2Score;
    private String status;
}
