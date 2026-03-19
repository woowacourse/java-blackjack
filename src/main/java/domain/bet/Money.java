package domain.bet;

import constant.PolicyConstant;
import exception.ErrorMessage;
import java.math.BigDecimal;

public record Money(
    int value
) {

    public Money {
        validateMoneyOutOfRange(value);
        validateMoneyUnit(value);
    }

    public static Money from(String input) {
        validate(input);
        return new Money(Integer.parseInt(input));
    }

    private static void validate(String input) {
        validateMoneyIsNumber(input);
        validateMoneyOutOfRange(input);
        validateMoneyUnit(Integer.parseInt(input));
    }

    private static void validateMoneyIsNumber(String input) {
        if (!isNumber(input)) {
            throw new IllegalArgumentException(ErrorMessage.MONEY_NOT_NUMBER.getMessage());
        }
    }

    private static void validateMoneyOutOfRange(String input) {
        int money;
        try {
            money = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.MONEY_OUT_OF_RANGE.getMessage());
        }
        validateMoneyOutOfRange(money);
    }

    private static void validateMoneyOutOfRange(int input) {
        if (!(PolicyConstant.MONEY_MIN_VALUE <= input && input <= PolicyConstant.MONEY_MAX_VALUE)) {
            throw new IllegalArgumentException(ErrorMessage.MONEY_OUT_OF_RANGE.getMessage());
        }
    }

    private static void validateMoneyUnit(int money) {
        if (money % PolicyConstant.MONEY_UNIT != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MONEY_UNIT.getMessage());
        }
    }

    private static boolean isNumber(String input) {
        try {
            new BigDecimal(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
