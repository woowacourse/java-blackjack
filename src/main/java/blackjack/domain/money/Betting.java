package blackjack.domain.money;

import java.util.Objects;

public class Betting {
    private static final long MIN_AMOUNT = 0L;

    private final long amount;

    public Betting(long amount) {
        validateMinAmount(amount);
        this.amount = amount;
    }

    private void validateMinAmount(long amount) {
        if (amount <= MIN_AMOUNT) {
            throw new IllegalArgumentException("배팅 금액은 0보다 커야합니다.");
        }
    }

    public Profit multiply(double multiplier) {
        return new Profit(Math.round(amount * multiplier));
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Betting betting)) {
            return false;
        }

        return amount == betting.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return "Betting{" +
                "amount=" + amount +
                '}';
    }
}
