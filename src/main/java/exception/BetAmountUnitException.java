package exception;

import domain.BetAmounts;

public class BetAmountUnitException extends IllegalArgumentException {
    public static final String INVALID_BET_AMOUNT_UNIT = String.format("배팅 금액은 %d 단위이어야 합니다.",
            BetAmounts.BET_AMOUNT_UNIT);

    public BetAmountUnitException(final String message) {
        super(message);
    }
}
