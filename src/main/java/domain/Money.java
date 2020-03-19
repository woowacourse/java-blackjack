package domain;

public class Money {
    public static final String ERROR_NUMBER_FORMAT_MESSAGE = "잘못된 금액 입력입니다.";
    private final int money;

    public Money(int money) {
        validateNumber(money);
        this.money = money;
    }

    private void validateNumber(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException(ERROR_NUMBER_FORMAT_MESSAGE);
        }
    }

    public int get() {
        return money;
    }

    public Money multiply(double mul) {
        return new Money((int) (mul * money));
    }
}
