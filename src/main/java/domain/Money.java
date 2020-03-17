package domain;

public class Money {
    private int money;

    public Money(int money) {
        validate(money);
        this.money = money;
    }

    private void validate(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("돈이 음수일 수 없습니다.");
        }
    }

    public int subtract(Money money) {
        return this.money - money.money;
    }

    public Money multiply(double multiplier) {
        return new Money((int)(this.money * multiplier));
    }

    public int getMoney() {
        return this.money;
    }
}
