package domain.bet;

import constant.PolicyConstant;
import exception.ErrorMessage;
import java.math.BigDecimal;

public record Money(
    int value
) {

    public Money {
        validateForConstructor(value);
    }

    private static void validateForConstructor(int value) {
        validateMoneyInRange(value);
        validateMoneyUnit(value);
    }

    public static Money from(String input) {
        validateForFrom(input);
        return new Money(Integer.parseInt(input));
    }

    private static void validateForFrom(String input) {
        validateMoneyIsNumber(input);
        validateMoneyWithinIntRange(input);
    }

    private static void validateMoneyIsNumber(String input) {
        if (!isNumber(input)) {
            throw new IllegalArgumentException(ErrorMessage.MONEY_NOT_NUMBER.getMessage());
        }
    }

    private static void validateMoneyWithinIntRange(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.MONEY_OUT_OF_RANGE.getMessage());
        }
    }

    private static void validateMoneyInRange(int input) {
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
