package blackjack.domain.money;

import java.util.Objects;

public class Profit {

    private final int value;

    public Profit(int value) {
        this.value = value;
    }

    public static Profit win(BetAmount amount) {
        return new Profit(amount.toInt());
    }

    public static Profit win(BetAmount amount, double multiplier) {
        return new Profit((int) (amount.toInt() * multiplier));
    }

    public static Profit lose(BetAmount amount) {
        return new Profit(-1 * amount.toInt());
    }

    public Profit add(Profit profit) {
        return new Profit(this.value + profit.value);
    }

    public int toInt() {
        return value;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Profit profit = (Profit) object;
        return value == profit.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
