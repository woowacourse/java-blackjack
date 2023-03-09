package blackjack.controller;

import java.util.Arrays;

public enum DrawOrStay {

    DRAW("y", true),
    STAY("n", false);

    static final String INPUT_FORM_EXCEPTION_MESSAGE = "'y'또는 'n'을 입력해 주세요;";

    private final String input;
    private final boolean isDraw;

    DrawOrStay(String input, boolean isDraw) {
        this.input = input;
        this.isDraw = isDraw;
    }

    public static DrawOrStay from(String input) {
        return Arrays.stream(DrawOrStay.values())
                .filter(drawInput -> drawInput.input.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(INPUT_FORM_EXCEPTION_MESSAGE));
    }

    public boolean isDraw() {
        return isDraw;
    }
}
