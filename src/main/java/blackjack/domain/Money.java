package blackjack.domain;

import blackjack.utils.Validator;

public class Money {

    public static final String POSITIVE_NUMBER_FORMAT = "^[1-9]\\d*$";
    public static final String POSITIVE_NUMBER_MESSAGE = "배팅 금액은 양수로 입력해주세요.";

    private final int money;

    private Money(int money) {
        this.money = money;
    }

    public static Money of(String input) {
        Validator.validateNullOrEmpty(input);
        validatePositiveNumber(input);
        return new Money(Integer.parseInt(input));
    }

    private static void validatePositiveNumber(String input) {
        if (!input.matches(POSITIVE_NUMBER_FORMAT)) {
            throw new IllegalArgumentException(POSITIVE_NUMBER_MESSAGE);
        }
    }

    public int getMoney() {
        return money;
    }
}
