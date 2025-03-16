package domain.participant.bet;

import java.util.Objects;

public class BetAmount {

    private static final double BLACK_JACK_PROFIT = 1.5;
    private static final long MIN_AMOUNT = 0L;

    private final long amount;

    private BetAmount(final long amount) {
        validate(amount);
        this.amount = amount;
    }

    public static BetAmount from(final long amount) {
        return new BetAmount(amount);
    }

    public Long calculateBlackjackProfit() {
        return Math.round(this.amount * BLACK_JACK_PROFIT);
    }

    public Long plus(final Long other) {
        return this.amount + other;
    }

    private void validate(final long amount) {
        if (amount <= MIN_AMOUNT) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 1원 이상이어야 합니다.");
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final BetAmount betAmount = (BetAmount) o;
        return amount == betAmount.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }

    @Override
    public String toString() {
        return this.amount + "";
    }

    public long getValue() {
        return amount;
    }
}
