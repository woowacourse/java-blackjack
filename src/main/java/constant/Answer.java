package constant;

import java.util.Arrays;

public enum Answer {
    YES("y"),
    NO("n"),
    ;

    private final String value;

    Answer(String value) {
        this.value = value;
    }

    public static Answer getAnswer(String input) {
        return Arrays.stream(Answer.values())
                .filter(answer -> answer.value.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 입력입니다."));
    }
}
