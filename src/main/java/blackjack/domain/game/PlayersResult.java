package blackjack.domain.game;

import blackjack.domain.participant.Player2;
import java.util.Map;

public class PlayersResult {

    private final Map<Player2, Result2> results;

    public PlayersResult(Map<Player2, Result2> results) {
        this.results = results;
    }

    public int dealerWins() {
        return (int) results.values().stream()
                .filter(Result2::isLoss)
                .count();
    }

    public int dealerLosses() {
        return (int) results.values().stream()
                .filter(Result2::isWin)
                .count();
    }

    public int dealerTies() {
        return (int) results.values().stream()
                .filter(Result2::isTie)
                .count();
    }

    public Map<Player2, Result2> getResults() {
        return Map.copyOf(results);
    }
}
