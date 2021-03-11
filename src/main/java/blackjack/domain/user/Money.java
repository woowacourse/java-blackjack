package blackjack.domain.user;

public class Money {
    private static final int MINIMUM_BOUND = 0;

    private final int money;

    public Money(int money) {
        validate(money);
        this.money = money;
    }

    private void validate(int money) {
        if (money <= MINIMUM_BOUND) {
            throw new IllegalArgumentException("베팅 금액은 0원 이하일 수 없습니다.");
        }
    }
}
