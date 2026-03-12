package domain;

public class Money {
    public static final String ERROR_MONEY_REQUIRE_OVER_ZERO = "[ERROR] 금액은 0 이상이어야 합니다.";
    public static final String ERROR_MONEY_INSUFFICIENT = "[ERROR] 잔액이 부족합니다.";
    private final double amount;

    public Money(double amount) {
        this.amount = amount;
    }

    public double amount() {
        return amount;
    }

    public Money plus(double value) {
        return new Money(amount + value);
    }

    public Money minus(int value) {
        return new Money(amount - value);
    }
}
