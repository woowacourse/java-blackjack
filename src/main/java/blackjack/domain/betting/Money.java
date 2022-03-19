package blackjack.domain.betting;

import java.util.Objects;

public class Money {

    private final int betting;

    public Money(int betting) {
        this.betting = betting;
    }

    public int betting() {
        return betting;
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
