package domain.participant;

public class Money {
    private final int money;

    public Money(final int money) {
        validateDivisibilityRule(money);
        this.money = money;
    }

    private void validateDivisibilityRule(final int money) {
        if (money % 10000 != 0 || money <= 0) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 만원 단위입니다.");
        }
    }

    public int getMoney() {
        return money;
    }
}
