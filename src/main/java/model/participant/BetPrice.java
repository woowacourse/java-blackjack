package model.participant;

import constant.ErrorMessage;

public record BetPrice(int value) {
    private static final int MAX_BET = 100000000;

    public BetPrice {
        validate(value);
    }

    private static void validate(int value) {
        if(value <= 0 || value > MAX_BET) {
            throw new IllegalArgumentException(ErrorMessage.OUT_OF_RANGE_BET.getMessage());
        }
    }
}
