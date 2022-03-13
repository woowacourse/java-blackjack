package blackjack.domain;

import java.util.Arrays;

public enum HitOrStayAnswer {

    HIT_ANSWER("y"),
    STAY_ANSWER("n"),
    ;

    private final String answer;

    HitOrStayAnswer(String answer) {
        this.answer = answer;
    }

    public static boolean containsValue(String answer) {
        return Arrays.stream(HitOrStayAnswer.values())
                .anyMatch(hitOrStayAnswer -> hitOrStayAnswer.get().equals(answer));
    }

    public String get() {
        return answer;
    }
}
