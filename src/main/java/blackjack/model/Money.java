package blackjack.model;

public class Money {
    private final int money;

    public Money(final int money) {
        validatePositive(money);
        this.money = money;
    }

    private void validatePositive(final int money) {
        if (money <= 0) {
            throw new IllegalArgumentException("0원 이하의 금액을 베팅할 수 없습니다.");
        }
    }

    public Money multiply(final double multiplier) {
        return new Money((int) (money * multiplier));
    }

    public int getMoney() {
        return money;
    }
}
