package domain.game;

import domain.user.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import static domain.game.Result.values;

public class PlayerResults {
    private final Map<Player, Result> playerResults;

    public PlayerResults(Map<Player, Result> playerResults) {
        this.playerResults = playerResults;
    }

    public DealerResult generateDealerResult() {
        Map<Result, Integer> dealerResult = Arrays.stream(values())
                .collect(Collectors.toMap(
                        Result::reverse,
                        this::countResults
                ));
        return new DealerResult(dealerResult);
    }

    private int countResults(Result result) {
        return (int) playerResults.values()
                .stream()
                .filter(playerResult -> playerResult == result)
                .count();
    }

    public Map<Player, Result> getPlayerResults() {
        return Collections.unmodifiableMap(playerResults);
    }
}
