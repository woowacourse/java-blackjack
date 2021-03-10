package blackjack.domain.user;

import java.util.Objects;

public class Money {

    private static final String MIN_MONEY_ERROR = "[ERROR] 배팅 금액은 0원이 될 수 없습니다.";
    private final double money;

    public Money(double money) {
        validate(money);
        this.money = money;
    }

    private void validate(double money) {
        if (money < 1) {
            throw new IllegalArgumentException(MIN_MONEY_ERROR);
        }
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
