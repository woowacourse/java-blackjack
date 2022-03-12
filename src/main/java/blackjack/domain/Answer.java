package blackjack.domain;

import java.util.Arrays;

public enum Answer {

    DRAW("y", true),
    STAY("n", false),
    ;

    private final String input;
    private final boolean answer;

    Answer(final String input, final boolean answer) {
        this.input = input;
        this.answer = answer;
    }

    public static boolean isDraw(final String input) {
        return Arrays.stream(Answer.values())
                .filter(answer -> answer.input.equalsIgnoreCase(input))
                .map(Answer::getAnswer)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("y, n 중에서 입력해주세요."));
    }

    private boolean getAnswer() {
        return answer;
    }
}
