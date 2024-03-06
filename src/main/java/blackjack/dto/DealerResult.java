package blackjack.dto;

public class DealerResult {
    private final int wins;
    private final int loses;

    public DealerResult(final int wins, final int loses) {
        this.wins = wins;
        this.loses = loses;
    }

    public int getWins() {
        return wins;
    }

    public int getLoses() {
        return loses;
    }
}
