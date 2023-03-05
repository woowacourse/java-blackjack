package blackjack.domain.player;

import java.util.Arrays;

public enum Answer {
    YES("y"),
    STAY("n");

    private final String answer;

    Answer(String answer) {
        this.answer = answer;
    }

    public static Answer of(String inputAnswer) {
        return Arrays.stream(Answer.values())
                .filter(answer -> answer.answer.equals(inputAnswer))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("y 또는 n 으로 입력해주세요."));
    }

    public boolean isYes() {
        return this == YES;
    }
}
