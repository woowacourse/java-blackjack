package view;

import domain.Answer;
import java.util.Arrays;

public enum AnswerView {
    
    HIT(Answer.HIT, "y"),
    STAY(Answer.STAY, "n");

    private final Answer answer;
    private final String value;

    AnswerView(Answer answer, String value) {
        this.answer = answer;
        this.value = value;
    }

    public static Answer from(final String value) {
        final AnswerView result = Arrays.stream(AnswerView.values())
                .filter(answerView -> answerView.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 유효하지 않은 대답입니다."));

        return result.answer;
    }
}
