package blackjack.domain.betting;

import java.util.Objects;

public class Money {

    public static final int MINIMUM = 0;

    private final int betting;
    private int profit;

    public Money(int amount) {
        validateAmount(amount);
        this.betting = amount;
    }

    private void validateAmount(int amount) {
        if (amount < MINIMUM) {
            throw new IllegalArgumentException("[ERROR] 금액은 음수일 수 없습니다.");
        }
    }

    public void win() {
        this.profit += this.betting;
    }

    public void lose() {
        this.profit -= this.betting;
    }

    public void winByBlackjack() {
        this.profit += this.betting * 1.5;
    }

    public int profit() {
        return this.profit;
    }

    public void add(Money money) {
        this.profit += money.profit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money)) return false;
        Money money = (Money) o;
        return betting == money.betting;
    }

    @Override
    public int hashCode() {
        return Objects.hash(betting);
    }

}
