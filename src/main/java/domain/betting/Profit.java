package domain.betting;

import java.util.Objects;

public class Profit {
    private final int money;

    private Profit(final int money) {
        this.money = money;
    }

    public static Profit of(Bet bet, ProfitRate profitRate) {
        return new Profit((int) (bet.getMoney() * profitRate.get()));
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
        Profit profit = (Profit) o;
        return money == profit.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
