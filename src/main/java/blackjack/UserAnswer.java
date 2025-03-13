package blackjack;

import java.util.Arrays;

public enum UserAnswer {

    YES("y"),
    NO("n");

    private final String value;

    UserAnswer(final String value) {
        this.value = value;
    }

    public static UserAnswer of(final String input) {
        return Arrays.stream(UserAnswer.values())
                .filter(answer -> answer.value.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 응답입니다. 다시 입력해주세요."));
    }

    public boolean isYes() {
        return this == YES;
    }
}
