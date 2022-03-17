package blackjack.domain.betting;

import java.util.Objects;

public class Money {

    public static final int MINIMUM = 0;

    private int amount;

    public Money(int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    private void validateAmount(int amount) {
        if (amount < MINIMUM) {
            throw new IllegalArgumentException("[ERROR] 금액은 음수일 수 없습니다.");
        }
    }

    public void decrease(int amount) {
        this.amount -= amount;
    }

    public void increase(int amount) {
        this.amount += amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money)) return false;
        Money money = (Money) o;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
