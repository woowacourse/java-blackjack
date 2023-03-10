package blackjack.domain;

public final class Money {

    static final String SUBTRACT_ERROR_MESSAGE = "현재 금액보다 더 큰 금액을 뺄 수는 없습니다.";

    private final int money;

    public Money(final int money) {
        this.money = money;
    }

    public Money add(final Money money) {
        return new Money(this.money + money.money);
    }

    public Money subtract(final Money money) {
        if (this.money < money.money) {
            throw new IllegalArgumentException(SUBTRACT_ERROR_MESSAGE);
        }
        return new Money(this.money - money.money);
    }

    public int getMoney() {
        return money;
    }
}
