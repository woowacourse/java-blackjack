package blackjack.domain.user;

import java.util.Objects;

public class Money {

    private final double money;

    public Money(double money) {
        this.money = money;
    }

    public double getEarning(double earningRate) {
        return money * earningRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money that = (Money) o;
        return Double.compare(that.money, money) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
