package domain.answer;

import java.util.Arrays;

public enum Answer {

    YES("y"),
    NO("n")
    ;

    private static final String INVALID_INPUT_ERROR = String.format("%s 또는 %s을 입력해주세요.", YES.answer, NO.answer);

    private final String answer;

    Answer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public static Answer from(String value) {
        return Arrays.stream(Answer.values())
                .filter(v -> v.answer.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_INPUT_ERROR)
                );
    }

    public boolean isNo() {
        return this.equals(Answer.NO);
    }

    public boolean isYes() {
        return this.equals(Answer.YES);
    }

}
