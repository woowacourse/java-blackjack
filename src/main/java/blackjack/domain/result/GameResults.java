package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResults {

    private final Map<Participant, GameResult> playerResults;

    private GameResults(final Map<Participant, GameResult> playerResults) {
        this.playerResults = playerResults;
    }

    public static GameResults create(final Players players, final Dealer dealer) {
        Map<Participant, GameResult> playerResults = new LinkedHashMap<>();

        for (Player player : players.players()) {
            GameResult playerResult = GameResult.of(player.calculateScore(), dealer.calculateScore());
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

    public Map<Participant, GameResult> playerResults() {
        return playerResults;
    }
}
