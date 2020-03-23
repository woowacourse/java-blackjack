package domain.gambler;

import java.util.Objects;

public class Money {

    private static final String NEGATIVE_EXCEPTION_MESSAGE = "금액은 음수가 아니어야 합니다";
    private static final String MINIMUM_UNIT_EXCEPTION_MESSAGE = "금액의 최소 단위는 10입니다.";
    public static final int ZERO = 0;
    public static final int TENS = 10;

    private final int money;

    private Money(int money) {
        this.money = money;
    }

    public static Money fromPositive(String input) {
        try {
            int money = Integer.parseInt(input);
            validateMoney(money);
            return new Money(money);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static void validateMoney(int money) {
        if (money < ZERO) {
            throw new IllegalArgumentException(NEGATIVE_EXCEPTION_MESSAGE);
        }
        if (money % TENS != ZERO) {
            throw new IllegalArgumentException(MINIMUM_UNIT_EXCEPTION_MESSAGE);
        }
    }

    public Money multiply(double ratio) {
        return new Money((int) (money * ratio));
    }

    public Money add(Money money) {
        return new Money(this.money + money.getMoney());
    }

    public int getMoney() {
        return money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money1 = (Money) o;
        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }

    @Override
    public String toString() {
        return String.valueOf(money);
    }
}
