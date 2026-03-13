package model.participant;

import constant.ErrorMessage;

public record BetPrice(Integer value) {
    public BetPrice {
        validate(value);
    }

    private static void validate(Integer value) {
        if(value <= 0 || value > 1000000000) {
            throw new IllegalArgumentException(ErrorMessage.OUT_OF_RANGE_BET.getMessage());
        }
    }
}
