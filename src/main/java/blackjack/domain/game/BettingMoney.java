package blackjack.domain.game;

import blackjack.constants.ErrorCode;
import blackjack.domain.game.exception.InvalidMoneyValueException;
import java.math.BigDecimal;

public class BettingMoney {
    private static final int BETTING_MONEY_UNIT = 1000;
    private static final int MAX_BETTING_MONEY_BOUND = 10_000_000;

    private final BigDecimal value;

    public BettingMoney(int value) {
        validateUnit(value);
        validateMaxBound(value);
        this.value = BigDecimal.valueOf(value);
    }

    private void validateUnit(final int value) {
        if (isInvalidUnit(value)) {
            throw new InvalidMoneyValueException(ErrorCode.INVALID_MONEY_UNIT);
        }
    }

    private boolean isInvalidUnit(final int value) {
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

    public int getValue() {
        return value.intValue();
    }
}
