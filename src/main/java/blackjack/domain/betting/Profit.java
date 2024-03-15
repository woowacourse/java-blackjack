package blackjack.domain.betting;

import blackjack.domain.cardgame.WinningStatus;

import static blackjack.domain.cardgame.WinningStatus.LOSE;
import static blackjack.domain.cardgame.WinningStatus.PUSH;
import static blackjack.domain.cardgame.WinningStatus.WIN;

public class Profit {
    private final int value;

    Profit(final int value) {
        this.value = value;
    }

    public static Profit of(final Money money, final WinningStatus status) {
        if (WIN.equals(status)) {
            return new Profit(money.value());
        }
        if (LOSE.equals(status)) {
            return new Profit(-money.value());
        }
        if (PUSH.equals(status)) {
            return new Profit(0);
        }

        throw new IllegalStateException();
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Profit profit = (Profit) o;

        return value == profit.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
