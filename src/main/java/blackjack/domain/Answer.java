package blackjack.domain;

import blackjack.utils.Validator;

import java.util.Arrays;

public enum Answer {

    GIVEN("y", true),
    NOT_GIVEN("n", false)
    ;

    private static final String RECEIVED_FLAG_MESSAGE = "y, n 중에서 입력해주세요.";

    private final String symbol;
    private final boolean isGiven;

    Answer(final String symbol, final boolean isGiven) {
        this.symbol = symbol;
        this.isGiven = isGiven;
    }

    public static boolean findBySymbol(final String receivedFlag) {
        Validator.validateNullOrEmpty(receivedFlag);
        return Arrays.stream(Answer.values())
                .filter(answer -> receivedFlag.equalsIgnoreCase(answer.symbol))
                .findFirst()
                .map(answer -> answer.isGiven)
                .orElseThrow(() -> new IllegalArgumentException(RECEIVED_FLAG_MESSAGE));
    }
}
