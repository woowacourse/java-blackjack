package blackjack.domain.participants;

public record Profit(long value) {
    private static final long BLACKJACK_PAYOUT_NUMERATOR = 3;
    private static final long BLACKJACK_PAYOUT_DENOMINATOR = 2;

    public static Profit zero() {
        return new Profit(0L);
    }

    public Profit negative() {
        return new Profit(this.value * -1);
    }

    public Profit applyBlackjackPayout() {
        return new Profit(this.value * BLACKJACK_PAYOUT_NUMERATOR / BLACKJACK_PAYOUT_DENOMINATOR);
    }
}
