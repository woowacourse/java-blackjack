package domain;

import java.util.Objects;

public class PlayerAnswer {
    private static final String INPUT_TRUE = "y";
    private static final String INPUT_FALSE = "n";

    private final boolean answer;

    public PlayerAnswer(String answer) {
        validateValue(answer);
        this.answer = INPUT_TRUE.equals(answer);
    }

    private void validateValue(String answer) {
        validateNull(answer);
        if (!INPUT_TRUE.equals(answer) && !INPUT_FALSE.equals(answer)) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }

    private void validateNull(String answer) {
        if (Objects.isNull(answer)) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }

    public boolean isAgree() {
        return answer;
    }
}
