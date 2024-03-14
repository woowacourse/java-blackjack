package domain.dto;

import domain.participant.PlayerName;

import java.util.Map;

public record BettingResult(Map<PlayerName, Integer> result, int DealerResult) {
}
