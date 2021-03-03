package blackjack.domain;

import java.util.Arrays;

public enum DrawAnswer {
    YES("y"),
    NO("n");
    private final String answer;
    DrawAnswer(String answer) {
        this.answer = answer;
    }

    public static DrawAnswer find(String answer){
        return Arrays.stream(values())
                .filter(value -> value.answer.equals(answer))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
