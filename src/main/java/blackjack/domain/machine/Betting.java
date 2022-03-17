package blackjack.domain.machine;

import java.util.Objects;

public class Betting {
    private final int money;

    public Betting(int money) {
        this.money = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Betting)) {
            return false;
        }
        Betting betting = (Betting) o;
        return money == betting.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
