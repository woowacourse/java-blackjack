package blackjack.domain;

import blackjack.utils.Validator;

public class Money {

    public static final String POSITIVE_NUMBER_FORMAT = "^[1-9]\\d*$";
    public static final String TEN_UNITS_NUMBER_FORMAT = "^[1-9]\\d*0$";
    public static final String POSITIVE_NUMBER_MESSAGE = "배팅 금액은 양수로 입력해주세요.";
    public static final String TEN_UNITS_NUMBER_MESSAGE = "배팅 금액은 10원 단위로 입력해주세요.";

    private final int money;

    private Money(int money) {
        this.money = money;
    }

    public static Money of(final String input) {
        Validator.validateNullOrEmpty(input);
        validate(input);
        return new Money(Integer.parseInt(input));
    }

    private static void validate(final String input) {
        validatePositiveNumber(input);
        validateTenUnitsNumber(input);
    }

    private static void validatePositiveNumber(final String input) {
        if (!input.matches(POSITIVE_NUMBER_FORMAT)) {
            throw new IllegalArgumentException(POSITIVE_NUMBER_MESSAGE);
        }
    }

    private static void validateTenUnitsNumber(final String input) {
        if (!input.matches(TEN_UNITS_NUMBER_FORMAT)) {
            throw new IllegalArgumentException(TEN_UNITS_NUMBER_MESSAGE);
        }
    }

    public static Money profits(final double rateOfReturn, final Money money) {
        return new Money((int) (rateOfReturn * money.money));
    }

    public int getMoney() {
        return money;
    }
}
