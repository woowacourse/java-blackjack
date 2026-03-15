package util;

import domain.betting.Money;

public class InputBettingParser {
    private InputBettingParser() {
    }

    public static Money parseBettingMoney(String inputBettingMoney) {
        try {
            long bettingMoney = Long.parseLong(inputBettingMoney);
            return new Money(bettingMoney);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }
    }
}
