package blackjack.domain;

import java.util.Arrays;

public enum DrawOrStay {

    DRAW("y"),
    STAY("n");

    static final String INPUT_FORM_EXCEPTION_MESSAGE = "'y'또는 'n'을 입력해 주세요;";

    private final String input;

    DrawOrStay(final String input) {
        this.input = input;
    }

    public static DrawOrStay from(final String input) {
        return Arrays.stream(DrawOrStay.values())
                .filter(drawInput -> drawInput.input.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(INPUT_FORM_EXCEPTION_MESSAGE));
    }

    public boolean isDraw() {
        return this == DRAW;
    }
}
