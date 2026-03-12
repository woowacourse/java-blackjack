package domain.answer;

import domain.answer.exception.AnswerException;
import domain.answer.exception.ErrorMessage;

import java.util.Arrays;

public enum DrawDecision {

    YES("y"),
    NO("n")
    ;

    private final String decision;

    DrawDecision(String decision) {
        this.decision = decision;
    }

    public String getDecision() {
        return decision;
    }

    public static DrawDecision from(String userDecision) {
        return Arrays.stream(DrawDecision.values())
                .filter(v -> v.decision.equals(userDecision))
                .findFirst()
                .orElseThrow(() -> new AnswerException(ErrorMessage.DRAW_DECISION_INPUT_ERROR)
                );
    }

    public boolean isYes() {
        return this.equals(DrawDecision.YES);
    }

}
