package blackjack.domain.player;

import java.util.Objects;

public class Bet {

    private static final int MIN = 0;

    private final int amount;

    public Bet(final int amount) {
        checkBetRightRange(amount);
        this.amount = amount;
    }

    private void checkBetRightRange(int bet) {
        if (bet <= MIN) {
            throw new IllegalArgumentException("[ERROR] 베팅은 1원부터 가능합니다.");
        }
    }

    public int getAmount() {
        return amount;
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
