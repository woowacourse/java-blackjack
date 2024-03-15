package blackjack.model.money;

import java.util.Objects;

public class BetMoney {
    private final int betMoney;

    public BetMoney(final int betMoney) {
        this.betMoney = betMoney;
    }

    public BetMoney multiply(final double multiplier) {
        return new BetMoney((int) (betMoney * multiplier));
    }

    public int getBetMoney() {
        return betMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BetMoney betMoney1 = (BetMoney) o;
        return betMoney == betMoney1.betMoney;
    }

    @Override
    public int hashCode() {
        return Objects.hash(betMoney);
    }
}
