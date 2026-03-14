package blackjack.domain.gameresult;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResults {
    private final Map<Player, Integer> gameResults;

    private GameResults(Map<Player, Integer> gameResults) {
        this.gameResults = gameResults;
    }

    public static GameResults of(Dealer dealer, Players players) {
        Map<Player, Integer> results = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            GameResult gameResult = GameResult.matchResult(player, dealer);
            results.put(player, player.calculateProfit(gameResult.getRatio()));
        }
        return new GameResults(results);
    }

    public Map<Player, Integer> getPlayersProfit() {
        return Collections.unmodifiableMap(gameResults);
    }

    public int getDealerProfit() {
        return gameResults.values().stream()
                .mapToInt(value -> -value)
                .sum();
    }
}
