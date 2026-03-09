package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResults {

    private static final GameResultCalculator calculator = new GameResultCalculator();

    private final Map<Player, GameResult> playerResults;

    private GameResults(final Map<Player, GameResult> playerResults) {
        this.playerResults = playerResults;
    }

    public static GameResults create(final Players players, final Dealer dealer) {
        Map<Player, GameResult> playerResults = new LinkedHashMap<>();

        for (Player player : players.players()) {
            GameResult playerResult = calculator.calculate(player.calculateScore(), dealer.calculateScore());
            playerResults.put(player, playerResult);
        }
        return new GameResults(playerResults);
    }

    public Map<GameResult, Integer> dealerResult() {
        Map<GameResult, Integer> dealerResults = new EnumMap<>(GameResult.class);

        for (GameResult playerResult : playerResults.values()) {
            dealerResults.merge(playerResult.reverse(), 1, Integer::sum);
        }
        return dealerResults;
    }

    public Map<Player, GameResult> playerResults() {
        return playerResults;
    }
}
