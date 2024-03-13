package exception;

import domain.BetAmounts;

public class BetAmountRangeException extends IllegalArgumentException {
    public static final String INVALID_AMOUNT_RANGE = String.format("배팅 금액은 최소 %d원, 최대 %d까지 가능합니다.",
            BetAmounts.BET_MIN_AMOUNT, BetAmounts.BET_MAX_AMOUNT);

    public BetAmountRangeException(final String message) {
        super(message);
    }
}
