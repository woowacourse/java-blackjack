package blackjack.domain.answer;

import java.util.Arrays;

public enum Answer {

    YES("y"),
    NO("n"),
    ;

    private static final String HIT_OR_STAND_ERROR_MESSAGE = "예는 y, 아니오는 n을 입력해 주세요.";

    private final String value;

    Answer(String value) {
        this.value = value;
    }

    public static Answer of(String input) {
        return Arrays.stream(Answer.values())
            .filter(answer -> answer.value.equals(input.toLowerCase()))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(HIT_OR_STAND_ERROR_MESSAGE));
    }

    public boolean isHit() {
        return value.equals(YES.value);
    }
}
