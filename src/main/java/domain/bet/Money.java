package domain.bet;

import exception.ErrorMessage;

public record Money(
    int value
) {


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
        if (!(0 < money && money <= 100_000_000)) {
            throw new IllegalArgumentException(ErrorMessage.MONEY_OUT_OF_RANGE.getMessage());
        }
    }

    private static void validateMoneyUnit(int money) {
        if (money % 10 != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MONEY_UNIT.getMessage());
        }
    }

    private static boolean isNumber(String input) {
        for (char number : input.toCharArray()) {
            if (!Character.isDigit(number)) {
                return false;
            }
        }
        return true;
    }
}
