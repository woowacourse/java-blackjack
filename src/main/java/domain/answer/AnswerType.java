package domain.answer;

import java.util.Arrays;

public enum AnswerType {
    YES("y"),
    NO("n");

    private String answer;

    AnswerType(String answer) {
        this.answer = answer;
    }

    public static AnswerType answerValueOf(String answer) {
        Answer inputAnswer = new Answer(answer);

        return Arrays.stream(values())
                .filter(answerType -> answerType.answer.equals(inputAnswer.getAnswer()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public boolean isYes() {
        return equals(AnswerType.YES);
    }

}
