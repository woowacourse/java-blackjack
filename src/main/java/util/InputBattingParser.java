package util;

import domain.betting.Money;

public class InputBattingParser {
    private InputBattingParser() {
    }

    public static Money parseBattingParser(String inputBattingMoney) {
        try {
            long battingMoney = Long.parseLong(inputBattingMoney);
            return new Money(battingMoney);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }
    }
}
