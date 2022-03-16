package blackjack.domain.bet;

public class Money {
    private final int money;

    public Money(int money) {
        validatePositive(money);
        this.money = money;
    }

    private void validatePositive(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException("금액은 양수여야 합니다.");
        }
    }
}
