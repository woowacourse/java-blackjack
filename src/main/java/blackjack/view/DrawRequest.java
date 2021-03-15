package blackjack.view;

import blackjack.exception.InvalidInputException;
import java.util.Arrays;
import java.util.Locale;

public enum DrawRequest {
    YES("y", true), NO("n", false);

    private final String input;
    private final boolean answer;

    DrawRequest(String input, boolean answer) {
        this.input = input;
        this.answer = answer;
    }

    public static boolean drawOrStay(String input) {
        return Arrays.stream(DrawRequest.values())
                .filter(drawRequest -> drawRequest.input.equals(input.toLowerCase(Locale.ROOT)))
                .findFirst()
                .orElseThrow(() -> new InvalidInputException(
                        String.format("%s, %s 중에서 선택해주세요", YES.input, NO.input)))
                .answer;
    }
}