package domain.betting;

import java.util.Objects;

public class Bet {
    private static final int MINIMUM_BETTING_MONEY = 1;
    static final String INVALID_BETTING_MESSAGE = "게임에 참가하기 위해 베팅은 반드시 해주세요";

    private final int money;

    public Bet(final int money) {
        validateRange(money);
        this.money = money;
    }

    public void validateRange(final int money) {
        if (money < MINIMUM_BETTING_MONEY) {
            throw new IllegalArgumentException(INVALID_BETTING_MESSAGE);
        }
    }

    public int getMoney() {
        return money;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bet bet = (Bet) o;
        return money == bet.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
