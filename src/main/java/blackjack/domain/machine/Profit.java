package blackjack.domain.machine;

import blackjack.domain.participant.Player;
import java.util.Objects;

public class Profit {
    private final long money;

    public Profit(long money) {
        this.money = money;
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
