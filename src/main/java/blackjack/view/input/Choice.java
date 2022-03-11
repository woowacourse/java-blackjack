package blackjack.view.input;

import java.util.Arrays;

public enum Choice {

    HIT("y", true),
    STAND("n", false);

    private final String answer;
    private final boolean continuable;

    Choice(final String answer, final boolean continuable) {
        this.answer = answer;
        this.continuable = continuable;
    }

    public static Choice from(final String answer) {
        return Arrays.stream(Choice.values())
                .filter(it -> it.matchesAnswer(answer))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("입력은 y 또는 n 이어야 합니다."));
    }

    private boolean matchesAnswer(final String answer) {
        final String lowerCasedAnswer = answer.toLowerCase();
        return lowerCasedAnswer.equals(this.answer);
    }

    public boolean getContinuable() {
        return continuable;
    }
}
