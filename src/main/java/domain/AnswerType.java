package domain;

import java.util.Arrays;

public enum AnswerType {
    YES("y"),
    NO("n");

    private String answer;

    AnswerType(String answer) {
        this.answer = answer;
    }

    public static AnswerType findAnswerType(String answer) {
        return Arrays.stream(values())
                .filter(answerType -> answerType.answer.equals(answer))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 문자를 입력하였습니다."));
    }
}
