package blackjack.domain.user;

import java.util.Objects;

public class Bet {
    private static final int MIN_MONEY_VALUE = 0;

    private final double amount;

    private Bet(double amount) {
        validateNaturalNumber(amount);
        this.amount = amount;
    }

    private void validateNaturalNumber(double amount) {
        if (amount <= MIN_MONEY_VALUE) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 0보다 커야됩니다.");
        }
    }

    public static Bet from(double amount) {
        return new Bet(amount);
    }

    public double calculate(double value) {
        return value * amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bet bet = (Bet) o;
        return amount == bet.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
