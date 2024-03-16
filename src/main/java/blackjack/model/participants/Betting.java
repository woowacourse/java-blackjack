package blackjack.model.participants;

import java.util.Objects;

public final class Betting {
    private static final int MINIMUM_AMOUNT = 0;

    private final int amount;

    public Betting(final int amount) {
        validateMoney(amount);
        this.amount = amount;
    }

    private void validateMoney(final int amount) {
        if (amount < MINIMUM_AMOUNT) {
            throw new IllegalArgumentException("금액은 " + MINIMUM_AMOUNT + "보다 작을 수 없다.");
        }
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Betting betting = (Betting) o;
        return amount == betting.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
