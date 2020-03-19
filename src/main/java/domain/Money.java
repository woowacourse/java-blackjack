package domain;

public class Money {
    private final int money;

    public Money(int money) {
        this.money = money;
    }

    public int getValue() {
        return money;
    }

    public Money multiply(double mul) {
        return new Money((int) (mul * money));
    }
}
