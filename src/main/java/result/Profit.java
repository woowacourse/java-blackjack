package result;

import participant.Bet;

public record Profit(int value) {
    public static Profit of(final Bet bet, final GameResult result) {
        int amount = bet.toInt();
        double earningRate = result.getEarningRate();
        return new Profit((int) (amount * earningRate));
    }

    public Profit add(final Profit other) {
        return new Profit(value + other.value);
    }

    public Profit negative() {
        return new Profit(value * -1);
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
