package domain.game;

import static domain.game.Result.values;

import domain.user.Player;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayerResults {
    private final Map<Player, Result> playerResults;

    public PlayerResults(Map<Player, Result> playerResults) {
        this.playerResults = playerResults;
    }

    public Map<Result, Integer> generateDealerResult() {
        return Arrays.stream(values())
                .collect(Collectors.toMap(
                        Result::reverse,
                        this::countResults
                ));
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
