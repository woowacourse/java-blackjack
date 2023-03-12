package blackjack.domain;

public class Profit {

    private final static int MIN_PROFIT = 1;
    private final static int MAX_PROFIT = 100_000_000;

    private final int value;

    private Profit(final int value) {
        this.value = value;
    }

    public static Profit from(final int value) {
        validateProfit(value);
        return new Profit(value);
    }

    private static void validateProfit(final int value) {
        if (value < MIN_PROFIT) {
            throw new IllegalArgumentException("베팅 금액은 1 이상이여야 합니다.");
        }
        if (value > MAX_PROFIT) {
            throw new IllegalArgumentException("베팅 금액은 100,000,000 이하여야 합니다.");
        }
    }

    public Profit calculateProfit(final Result result) {
        return new Profit((int) (this.value * result.getRate()));
    }

    public static Profit dealerProfit(final int dealerProfit) {
        return new Profit(dealerProfit);
    }

    public int getProfit() {
        return this.value;
    }
}
