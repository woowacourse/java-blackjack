package util;

import domain.betting.Money;

public class InputBettingParser {
    private InputBettingParser() {
    }

    public static Money parseBettingMoney(String inputBettingMoney) {
        try {
            long bettingMoney = Long.parseLong(inputBettingMoney.trim());
            return new Money(bettingMoney);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("숫자로만 입력해야합니다.");
        }
        catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception.getMessage());
        }
    }
}
