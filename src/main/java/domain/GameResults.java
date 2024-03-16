package domain;

import domain.constant.GameResult;
import domain.participant.PlayerName;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GameResults {
    private final Map<PlayerName, Integer> result;
    private final int dealerBettingResult;

    private GameResults(final Map<PlayerName, Integer> result, int dealerBettingResult) {
        this.result = result;
        this.dealerBettingResult = dealerBettingResult;
    }

    public static GameResults of(
            final Map<PlayerName, GameResult> playerGameResults,final Betting betting) {
        Map<PlayerName, Integer> result = new HashMap<>();
        playerGameResults.forEach(
                (key, value) -> result.put(key, getBettingResult(key, value, betting)));
        int dealerBettingResult = -1 * result.values().stream().reduce(0, Integer::sum);
        return new GameResults(result, dealerBettingResult);
    }

    public Map<PlayerName, Integer> getResult() {
        return Collections.unmodifiableMap(result);
    }

    public int getDealerBettingResult() {
        return dealerBettingResult;
    }

    private static int getBettingResult(
            final PlayerName playerName, final GameResult gameResult, final Betting betting) {
        return (int) (betting.getBetting(playerName).getAmount() * gameResult.getProfitRatio());
    }
}
