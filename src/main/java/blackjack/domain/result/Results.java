package blackjack.domain.result;

import blackjack.domain.player.Player;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class Results {

    private final Map<Player, Result> results;

    public Results() {
        results = new HashMap<>();
    }

    public void addResult(Player player, Result playerResult) {
        results.put(player, playerResult);
    }

    public Map<Result, Integer> findDealerResult() {
        Map<Result, Integer> dealerResults = new EnumMap<>(Result.class);

        for (Player player : results.keySet()) {
            Result result = convertResult(results.get(player));
            dealerResults.put(result, dealerResults.getOrDefault(result, 0) + 1);
        }

        return dealerResults;
    }

    private Result convertResult(Result playerResult) {
        if (playerResult == Result.DRAW) {
            return Result.DRAW;
        }

        if (playerResult == Result.LOSE) {
            return Result.WIN;
        }

        return Result.LOSE;
    }

    public Result getResults(Player player) {
        return results.get(player);
    }
}
