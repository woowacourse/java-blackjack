package blackjack.domain.game;

import blackjack.domain.participant.Player2;
import java.util.Map;

public class PlayersResult {

    private final Map<Player2, Result> results;

    public PlayersResult(Map<Player2, Result> results) {
        this.results = results;
    }

    public int dealerWins() {
        return (int) results.values().stream()
                .filter(Result::isLoss)
                .count();
    }

    public int dealerLosses() {
        return (int) results.values().stream()
                .filter(Result::isWin)
                .count();
    }

    public int dealerTies() {
        return (int) results.values().stream()
                .filter(Result::isTie)
                .count();
    }

    public Map<Player2, Result> getResults() {
        return Map.copyOf(results);
    }
}
