package blackjack.domain;

import blackjack.utils.Validator;

public class Answer {

    GIVEN_SYMBOL("y"),
    NOT_GIVEN_SYMBOL("n")
            ;

    private
    private static final String RECEIVED_FLAG_MESSAGE = "y, n 중에서 입력해주세요.";


    public static boolean answer(final String receivedFlag) {
        Validator.validateNullOrEmpty(receivedFlag);
        if (receivedFlag.equalsIgnoreCase(GIVEN_SYMBOL)) {
            return true;
        }
        if (receivedFlag.equalsIgnoreCase(NOT_GIVEN_SYMBOL)) {
            return false;
        }
        throw new IllegalArgumentException(RECEIVED_FLAG_MESSAGE);
    }
}
