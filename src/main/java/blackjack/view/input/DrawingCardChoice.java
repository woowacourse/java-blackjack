package blackjack.view.input;

import java.util.Arrays;

public enum DrawingCardChoice {

    YES("y"),
    NO("n");

    private final String answer;

    DrawingCardChoice(final String answer) {
        this.answer = answer;
    }

    public static boolean isChoiceYes(final String answer) {
        final DrawingCardChoice choice = DrawingCardChoice.from(answer);
        return choice.equals(YES);
    }

    private static DrawingCardChoice from(final String answer) {
        return Arrays.stream(DrawingCardChoice.values())
                .filter(it -> it.matchesAnswer(answer))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("입력은 y 또는 n 이어야 합니다."));
    }

    private boolean matchesAnswer(final String answer) {
        final String lowerCasedAnswer = answer.toLowerCase();
        return lowerCasedAnswer.equals(this.answer);
    }

}
