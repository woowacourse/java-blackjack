package util;

import domain.betting.Money;

public class InputBettingParser {
    private InputBettingParser() {
    }

    public static Money parseBattingParser(String inputBettingMoney) {
        try {
            long battingMoney = Long.parseLong(inputBettingMoney);
            return new Money(battingMoney);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }
    }
}
