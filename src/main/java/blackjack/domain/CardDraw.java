package blackjack.domain;

import blackjack.util.NullChecker;
import java.util.Arrays;

public enum CardDraw {
    YES('y'),
    NO('n');

    private static final int VALUE_LENGTH = 1;
    private static final String INVALID_INPUT_EXCEPTION_MESSAGE = "유효한 값 (y,n) 이 아닙니다.";

    private final char value;

    CardDraw(char value) {
        this.value = value;
    }

    public static CardDraw of(String input) {
        validationInput(input);
        return Arrays.stream(CardDraw.values())
            .filter(cardDraw -> cardDraw.value == input.toLowerCase().charAt(0))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(INVALID_INPUT_EXCEPTION_MESSAGE));
    }

    private static void validationInput(String input) {
        NullChecker.validateNotNull(input);
        if (input.length() != VALUE_LENGTH) {
            throw new IllegalArgumentException(INVALID_INPUT_EXCEPTION_MESSAGE);
        }
    }

    public boolean isYes() {
        return this == YES;
    }
}
