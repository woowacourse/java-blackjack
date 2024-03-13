package model.money;

public class Money {
    private final int amount;

    public Money(int money) {
        validate(money);
        this.amount = money;
    }

    public void validate(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("베팅 금액은 0보다 커야합니다.");
        }
    }

}
