package blackjack.domain.player;

import java.util.Arrays;

public enum Answer {
    YES("y"),
    NO("n");

    private final String answer;

    Answer(String answer) {
        this.answer = answer;
    }

    public static Answer of(String inputAnswer) {
        return Arrays.stream(Answer.values())
                .filter(answer -> answer.answer.equals(inputAnswer))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 입력값 입니다."));
    }
}
