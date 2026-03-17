package model.participant;

import constant.ErrorMessage;

public record BetPrice(long value) {
    private static final Long MAX_BET = 6_148_914_691_236_517_204L;

    public BetPrice {
        validate(value);
    }

    private static void validate(long value) {
        validateNegative(value);
        validateMaximum(value);
    }

    private static void validateNegative(long value) {
        if (value <= 0) {
            throw new IllegalArgumentException(ErrorMessage.NEGATIVE_BET.getMessage());
        }
    }

    private static void validateMaximum(long value) {
        if(value > MAX_BET) {
            throw new IllegalArgumentException(ErrorMessage.OUT_OF_MAXIMUM_RANGE_BET.getMessage());
        }
    }
}
