package blackjack.domain.user;

public class Money {
    private static final double MINIMUM_BOUND = 0.0;

    private final double money;

    public Money(double money) {
        validate(money);
        this.money = money;
    }

    private void validate(double money) {
        if (money <= MINIMUM_BOUND) {
            throw new IllegalArgumentException("베팅 금액은 0원 이하일 수 없습니다.");
        }
    }
}
