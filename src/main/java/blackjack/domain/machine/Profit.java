package blackjack.domain.machine;

import java.util.Objects;

public class Profit {
    private static final long INIT_MONEY = 0L;

    private final long money;

    public Profit(long money) {
        this.money = money;
    }

    public Profit() {
        money = INIT_MONEY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Profit)) {
            return false;
        }
        Profit profit = (Profit) o;
        return money == profit.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }

    public long getMoney() {
        return money;
    }
}
