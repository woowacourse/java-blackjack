package blackjack.domain.participant;

import java.util.Objects;

public class Money {

    public static final int MINIMUM_AMOUNT = 0;
    public static final Money ZERO = new Money(0);

    private final int value;

    public Money(int value) {
        this.value = value;
    }

    private void validateMinimumAmount(int value) {
        if (value < MINIMUM_AMOUNT) {
            throw new IllegalArgumentException(
                    String.format("배팅 금액은 %d원 이상이어야 합니다", MINIMUM_AMOUNT));
        }
    }

    public static Money getBettingMoney(String bettingMoneyInput) {
        return new Money(Integer.parseInt(bettingMoneyInput));
    }

    public Money multiplyByRate(double rate) {
        return new Money((int) (value * rate));
    }

    public Money add(Money winningMoney) {
        return new Money(value + winningMoney.value);
    }

    public Money toNegative() {
        return new Money(-1 * value);
    }

    public int toInt() {
        return value;
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
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Money{" +
                "value=" + value +
                '}';
    }
}
