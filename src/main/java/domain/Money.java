package domain;

public class Money {
    private final int money;

    public Money(int money) {
        validate(money);
        this.money = money;
    }

    private void validate(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }

    public int get() {
        return money;
    }

    public Money multiply(double mul) {
        return new Money((int) (mul * money));
    }
}
