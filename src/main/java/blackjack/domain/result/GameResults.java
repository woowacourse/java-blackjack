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
        Map<Player, GameResult> playerResults = new LinkedHashMap<>();
        Map<GameResult, Integer> dealerResults = new EnumMap<>(GameResult.class);

        for (Player player : players.getPlayers()) {
            GameResult playerResult = GameResult.of(player.calculateScore(), dealer.calculateScore());
            playerResults.put(player, playerResult);
            dealerResults.merge(playerResult.reverse(), 1, Integer::sum);
        }

        return new GameResults(playerResults, dealerResults);
    }

    public Map<Player, GameResult> getPlayerResults() {
        return playerResults;
    }

    public Map<GameResult, Integer> getDealerResults() {
        return dealerResults;
    }
}
