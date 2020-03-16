package domain;

import java.util.Arrays;

public enum Answer {
    YES("y"),
    NO("n");

    private String answer;

    Answer(String answer) {
        this.answer = answer;
    }

    public static Answer of(String answer) {
        String lowerAnswer = answer.toLowerCase();
        return Arrays.stream(values())
                .filter(an -> an.answer.equals(lowerAnswer))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("y 또는 n 을 입력해주세요."));
    }

    public boolean isYes() {
        return this == YES;
    }
}
