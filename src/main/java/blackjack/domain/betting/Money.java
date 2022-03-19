package blackjack.domain.betting;

import java.util.Objects;

public class Money {

    private static final int MINIMUM = 1000;

    private final int betting;

    public Money(int betting) {
        validateBetting(betting);
        this.betting = betting;
    }

    private void validateBetting(int betting) {
        if (betting < MINIMUM) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 " + MINIMUM + "이상이어야 합니다.");
        }
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
