package domain;

import java.util.Objects;

public class PlayerAnswer {
    private static final String INPUT_TRUE = "y";
    private static final String INPUT_FALSE = "n";
    private final boolean answer;

    public PlayerAnswer(String userAnswer) {
        validateValue(userAnswer);
        answer = INPUT_TRUE.equals(userAnswer);
    }

    private void validateValue(String userAnswer) {
        validateNull(userAnswer);
        if (!INPUT_TRUE.equals(userAnswer) && !INPUT_FALSE.equals(userAnswer)) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }

    private void validateNull(String userAnswer) {
        if (Objects.isNull(userAnswer)) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }

    public boolean isAgree() {
        return answer;
    }
}
