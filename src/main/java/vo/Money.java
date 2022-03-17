package vo;

public class Money {
    private final int money;

    private Money(int money) {
        this.money = money;
    }

    public static Money from(int money) {
        return new Money(money);
    }

    public int getMoney() {
        return money;
    }
}
