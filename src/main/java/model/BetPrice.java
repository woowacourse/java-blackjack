package model;

import constant.ErrorMessage;

public record BetPrice(Integer value) {
    public BetPrice {
        validate(value);
    }

    private static void validate(Integer value) {
        if(value < 0) {
            throw new IllegalArgumentException(ErrorMessage.NO_NEGATIVE_BET.getMessage());
        }
    }
}
