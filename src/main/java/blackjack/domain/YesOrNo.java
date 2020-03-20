package blackjack.domain;

import java.util.Arrays;
import java.util.Objects;

public enum YesOrNo {
    YES('y'),
    NO('n');

    private static final int VALUE_LENGTH = 1;
    private static final String INVALID_INPUT_EXCEPTION_MESSAGE = "유효한 값 (y,n) 이 아닙니다.";

    private final char value;

    YesOrNo(char value) {
        this.value = value;
    }

    public static YesOrNo of(String input) {
        validationInput(input);
        return Arrays.stream(YesOrNo.values())
            .filter(yesOrNo -> yesOrNo.value == input.toLowerCase().charAt(0))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(INVALID_INPUT_EXCEPTION_MESSAGE));
    }

    private static void validationInput(String input) {
        if (Objects.isNull(input) || input.length() != VALUE_LENGTH) {
            throw new IllegalArgumentException(INVALID_INPUT_EXCEPTION_MESSAGE);
        }
    }

    public boolean isYes() {
        return this == YES;
    }
}
