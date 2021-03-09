package blackjack.domain;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public enum UserAnswer {

    HIT("y"),
    STAY("n");

    private final String answer;

    UserAnswer(String answer) {
        this.answer = answer;
    }

    public static UserAnswer getUserAnswer(final String input) {
        validateNull(input);
        validateEmpty(input);
        String lowerInput = input.toLowerCase();
        validateHitOrStay(lowerInput);
        return Arrays.stream(UserAnswer.values())
                .filter(userAnswer -> userAnswer.answer.equals(lowerInput))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("카드 요청 입력값이 잘못됐습니다."));
    }

    private static void validateNull(final String input) {
        Objects.requireNonNull(input, "카드 요청 입력값은 null일 수 없습니다.");
    }

    private static void validateEmpty(final String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("카드 요청 입력값은 빈값이 될 수 없습니다.");
        }
    }

    private static void validateHitOrStay(final String input) {
        if (!input.equals(HIT.answer) && !input.equals(STAY.answer)) {
            throw new IllegalArgumentException("요청은 " + userAnswerValues() + "이어야 합니다.");
        }
    }

    private static String userAnswerValues() {
        return Arrays.stream(UserAnswer.values())
                .map(UserAnswer::getAnswer)
                .collect(Collectors.joining("또는 "));
    }

    public boolean isStay() {
        return STAY.equals(this);
    }

    public String getAnswer() {
        return answer;
    }
}
