package blackjack.domain.money;

import java.util.regex.Pattern;

public class Money {
    private static final String NOT_NUMERIC = "숫자 형식이 아닙니다.";
    private static final String NON_NEGATIVE_ERROR = "배팅할 금액을 음수일 수 없습니다.";
    private final int value;

    private Money(int value) {
        validatePositive(value);
        this.value = value;
    }

    public Money from(String money) {
        validateNumericString(money);
        return new Money(Integer.parseInt(money));
    }

    private void validateNumericString(String money) {
        if (!Pattern.compile("-?\\d+").matcher(money).matches()) {
            throw new IllegalArgumentException(NOT_NUMERIC);
        }
    }

    private void validatePositive(int money) {
        if (money < 0) {
            throw new IllegalArgumentException(NON_NEGATIVE_ERROR);
        }
    }

    public double applyRate(RewardRate rate) {
        return value * rate.get();
    }

    public int get() {
        return value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Money money = (Money) o;

        return value == money.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
