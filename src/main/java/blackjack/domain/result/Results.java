package blackjack.domain.result;

import blackjack.domain.player.Player;

import java.util.*;
import java.util.stream.Collectors;

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
            dealerResults.getOrDefault(convertResult(results.get(player)), 0);
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
