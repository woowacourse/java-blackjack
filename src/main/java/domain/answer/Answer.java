package domain.answer;

import java.util.Arrays;

public enum Answer {

    YES("y"),
    NO("n")
    ;

    private final String answer;

    private Answer(String ansewr) {
        this.answer = ansewr;
    }

    public String getAnswer() {
        return answer;
    }

    public static Answer from(String value) {
        return Arrays.stream(Answer.values())
                .filter(v -> v.answer.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("y 또는 n을 입력해주세요.")
                );
    }

    public boolean isNo() {
        return this.equals(Answer.NO);
    }

    public boolean isYes() {
        return this.equals(Answer.YES);
    }

}
