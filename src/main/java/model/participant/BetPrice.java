package model.participant;

import constant.ErrorMessage;

public record BetPrice(Integer value) {
    private static final Integer MAX_BET = 100000000;

    public BetPrice {
        validate(value);
    }

    private static void validate(Integer value) {
        if(value <= 0 || value > MAX_BET) {
            throw new IllegalArgumentException(ErrorMessage.OUT_OF_RANGE_BET.getMessage());
        }
    }
}
