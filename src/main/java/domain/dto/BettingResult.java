package domain.dto;

import domain.participant.PlayerName;

import java.util.Map;

public record BettingResult(Map<PlayerName, Integer> result) {
    public int getDealerBettingResult() {
        return -1 * result.values().stream().reduce(0, Integer::sum);
    }
}
