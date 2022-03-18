package blackjack.domain.money;

import blackjack.utils.IntegerUtils;

public class BettingMoney extends Money {

    public BettingMoney(int amount) {
        super(amount);
        validatePositive(amount);
    }

    public BettingMoney(String input) {
        this(IntegerUtils.parseInt(input));
    }

    private void validatePositive(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("금액은 0원 이하가 될 수 없습니다.");
        }
    }
}
