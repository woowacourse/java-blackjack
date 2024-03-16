package domain.dto;

import domain.participant.PlayerName;

import java.util.Map;

public record BettingResultDto(Map<PlayerName, Integer> result, int DealerBettingResult) {
}
