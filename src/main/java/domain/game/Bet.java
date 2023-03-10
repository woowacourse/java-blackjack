package domain.game;

import java.text.MessageFormat;
import java.util.Objects;

public final class Bet {

    public static final int MAX = 100_000_000;
    public static final double BONUS_RATE = 1.5;

    private final int bet;

    private Bet(final int bet) {
        validatePrice(bet);
        this.bet = bet;
    }

    public static Bet of(final int bet) {
        return new Bet(bet);
    }

    private void validatePrice(final int bet) {
        if (bet > MAX) {
            throw new IllegalArgumentException(
                    MessageFormat.format(
                            "{0}초과의 베팅은 할 수 없습니다.",
                            MAX
                    )
            );
        }
    }

    public int getBet() {
        return bet;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Bet bet1 = (Bet) o;
        return bet == bet1.bet;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bet);
    }

    @Override
    public String toString() {
        return "Bet{" +
                "bet=" + bet +
                '}';
    }
}
