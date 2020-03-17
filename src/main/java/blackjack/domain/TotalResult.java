package blackjack.domain;

import blackjack.domain.user.Player;

import java.util.HashMap;
import java.util.Map;

public class TotalResult {
    private Map<Player, Result> totalResult;

    public TotalResult(Map<Player, Result> totalResult) {
        this.totalResult = totalResult;
    }

    public Map<Result, Integer> calculatePlayerResultCount() {
        Map<Result, Integer> playerResult = new HashMap<>();
        playerResult.put(Result.WIN, 0);
        playerResult.put(Result.DRAW, 0);
        playerResult.put(Result.LOSE, 0);

        for (Result r : totalResult.values()) {
            playerResult.put(r, playerResult.get(r) + 1);
        }
        return playerResult;
    }

    public Map<Player, Result> getTotalResult() {
        return totalResult;
    }
}