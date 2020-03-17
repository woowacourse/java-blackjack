package blackjack.domain.result;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class GameResult {
    private final Map<ResultType, Integer> dealerResults;
    private final Map<Player, ResultType> playerResults;

    private GameResult(Map<ResultType, Integer> dealerResults, Map<Player, ResultType> playerResults) {
        this.dealerResults = dealerResults;
        this.playerResults = playerResults;
    }

    public static GameResult of(Dealer dealer, Players players) {
        Map<Player, ResultType> playerResults = new LinkedHashMap<>();

        for (Player player : players.getPlayers()) {
            playerResults.put(player, PlayerResult.of(player, dealer));
        }

        Map<ResultType, Integer> dealerResults = Arrays.stream(ResultType.values())
                .collect(Collectors.toMap(result -> result, result -> Collections.frequency(playerResults.values(), result.reverse()),
                        (v1, v2) -> {throw new AssertionError();},
                        LinkedHashMap::new));

        return new GameResult(dealerResults, playerResults);
    }

    public Map<ResultType, Integer> getDealerResults() {
        return dealerResults;
    }

    public Map<Player, ResultType> getPlayerResults() {
        return playerResults;
    }
}