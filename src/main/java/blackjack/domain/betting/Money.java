package blackjack.domain.betting;

import java.util.Objects;

public class Money {

    public static final int MINIMUM = 1000;

    private final int betting;

    public Money(int betting) {
        validateBetting(betting);
        this.betting = betting;
    }

    public int betting() {
        return betting;
    }

    private void validateBetting(int betting) {
        if (betting < MINIMUM) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 " + MINIMUM + "이상이어야 합니다.");
        }
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
