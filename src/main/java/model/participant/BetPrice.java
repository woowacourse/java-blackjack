package model.participant;

import constant.ErrorMessage;

public record BetPrice(long value) {
    public BetPrice {
        validate(value);
    }

    private static void validate(long value) {
        if(value <= 0) {
            throw new IllegalArgumentException(ErrorMessage.OUT_OF_RANGE_BET.getMessage());
        }
    }
}
