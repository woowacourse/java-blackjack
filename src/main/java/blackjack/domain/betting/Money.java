package blackjack.domain.betting;

import java.util.Objects;

public class Money {

    private static final double BLACKJACK_MULTIPLE = 1.5;

    private final int betting;
    private int profit;

    public Money(int amount) {
        this.betting = amount;
    }

    public void win() {
        this.profit += this.betting;
    }

    public void lose() {
        this.profit -= this.betting;
    }

    public void winByBlackjack() {
        this.profit += this.betting * BLACKJACK_MULTIPLE;
    }

    public int profit() {
        return this.profit;
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
