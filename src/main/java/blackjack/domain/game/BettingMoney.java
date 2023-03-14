package blackjack.domain.game;

import blackjack.constants.ErrorCode;
import blackjack.domain.game.exception.InvalidMoneyValueException;
import blackjack.domain.vo.Money;

public class BettingMoney {
    private static final int BETTING_MONEY_UNIT = 1000;
    private static final int MAX_BETTING_MONEY_BOUND = 10_000_000;

    private final Money money;

    public BettingMoney(int value) {
        validateUnit(value);
        validateMaxBound(value);
        this.money = new Money(value);
    }

    private void validateUnit(int value) {
        if (isInvalidUnit(value)) {
            throw new InvalidMoneyValueException(ErrorCode.INVALID_MONEY_UNIT);
        }
    }

    private boolean isInvalidUnit(int value) {
        return value % BETTING_MONEY_UNIT != 0;
    }

    private void validateMaxBound(int value) {
        if (isValidBound(value)) {
            throw new InvalidMoneyValueException(ErrorCode.INVALID_MONEY_BOUND);
        }
    }

    private boolean isValidBound(int value) {
        return value > MAX_BETTING_MONEY_BOUND;
    }

    public Money calculateProfit(double ratio) {
        return money.multiply(ratio);
    }

    public int getValue() {
        return money.getValue();
    }
}
