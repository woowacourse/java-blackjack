package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResults {

    private final Map<Player, GameResult> playerResults;
    private final Map<GameResult, Integer> dealerResults;

    private GameResults(
            final Map<Player, GameResult> playerResults,
            final Map<GameResult, Integer> dealerResults
    ) {
        this.playerResults = playerResults;
        this.dealerResults = dealerResults;
    }

    public static GameResults calculate(final Players players, final Dealer dealer) {
        final Map<Player, GameResult> playerResults = new LinkedHashMap<>();
        final Map<GameResult, Integer> dealerResults = new EnumMap<>(GameResult.class);
        players.getPlayers()
                .forEach(player -> addResult(player, dealer, playerResults, dealerResults));
        return new GameResults(playerResults, dealerResults);
    }

    private static void addResult(
            final Player player,
            final Dealer dealer,
            final Map<Player, GameResult> playerResults,
            final Map<GameResult, Integer> dealerResults
    ) {
        final GameResult result = GameResult.of(player.calculateScore(), dealer.calculateScore());
        playerResults.put(player, result);
        dealerResults.merge(result.reverse(), 1, Integer::sum);
    }

    public Map<Player, GameResult> getPlayerResults() {
        return playerResults;
    }

    public Map<GameResult, Integer> getDealerResults() {
        return dealerResults;
    }
}
