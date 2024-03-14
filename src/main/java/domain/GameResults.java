package domain;

import domain.constant.GameResult;
import domain.participant.PlayerName;

import java.util.HashMap;
import java.util.Map;

public record GameResults(Map<PlayerName, GameResult> playerGameResults) {
    public Map<PlayerName, Integer> calculateBettingOnPlayers(final Betting betting) {
        Map<PlayerName, Integer> result = new HashMap<>();
        playerGameResults().forEach((key, value) -> result.put(key, getBettingResult(key, value, betting)));
        return result;
    }

    private int getBettingResult(final PlayerName playerName, final GameResult gameResult, final Betting betting) {
        return (int) (betting.getBetting(playerName).getAmount() * gameResult.getProfitRatio());
    }
}
