package domain.answer;

import domain.answer.exception.AnswerException;
import domain.answer.exception.ErrorMessage;

import java.util.Arrays;

public enum Answer {

    YES("y"),
    NO("n")
    ;

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
                .orElseThrow(() -> new AnswerException(ErrorMessage.DRAW_DECISION_INPUT_ERROR)
                );
    }

    public boolean isNo() {
        return this.equals(Answer.NO);
    }

    public boolean isYes() {
        return this.equals(Answer.YES);
    }

}
