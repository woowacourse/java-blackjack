package domain;

import domain.constant.GameResult;
import domain.participant.PlayerName;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GameResults {
    private final Map<PlayerName, Integer> result;

    private GameResults(final Map<PlayerName, Integer> result) {
        this.result = result;
    }

    public static GameResults of(
            final Map<PlayerName, GameResult> playerGameResults,final Betting betting) {
        Map<PlayerName, Integer> result = new HashMap<>();
        playerGameResults.forEach(
                (key, value) -> result.put(key, getBettingResult(key, value, betting)));
        return new GameResults(result);
    }

    public Map<PlayerName, Integer> getResult() {
        return Collections.unmodifiableMap(result);
    }

    public int getDealerBettingResult() {
        return -1 * result.values().stream().reduce(0, Integer::sum);
    }

    private static int getBettingResult(
            final PlayerName playerName, final GameResult gameResult, final Betting betting) {
        return (int) (betting.getBetting(playerName).getAmount() * gameResult.getProfitRatio());
    }
}
