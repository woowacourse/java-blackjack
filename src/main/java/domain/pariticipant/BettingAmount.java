package domain.pariticipant;

import static constant.BlackjackConstant.BETTING_LIMIT;
import static exception.ErrorMessage.BETTING_LIMIT_OUT_OF_BOUNDS_ERROR;

public record BettingAmount(long bettingAmount) {

    public BettingAmount {
        if (bettingAmount < 0 || bettingAmount > BETTING_LIMIT) {
            throw new IllegalArgumentException(BETTING_LIMIT_OUT_OF_BOUNDS_ERROR.getMessage());
        }
    }
}
