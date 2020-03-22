package domain.result;

import java.util.function.Function;

public enum Result {
    BLACKJACK_WIN(betAmount -> (int) (betAmount * 1.5)),
    WIN(betAmount -> betAmount),
    DRAW(betAmount -> 0),
    LOSE(betAmount -> -betAmount);

    private final Function<Integer, Integer> getRevenue;

    Result(final Function<Integer, Integer> getRevenue) {
        this.getRevenue = getRevenue;
    }

    public int revenueOf(int betAmount) {
        return getRevenue.apply(betAmount);
    }
}
