package blackjack.model.participant;

public class BettingAmount {
    private final int money;

    public BettingAmount(final int money) {
        validatePositiveNumber(money);
        this.money = money;
    }

    private void validatePositiveNumber(final int money) {
        if (money < 0) {
            throw new IllegalArgumentException("배팅 금액은 양수만 가능합니다.");
        }
    }
}
