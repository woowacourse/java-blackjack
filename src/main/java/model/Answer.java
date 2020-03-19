package model;

import exception.IllegalYesOrNoInputException;
import java.util.Arrays;
import utils.StringUtils;

public enum Answer {
    YES("y"),
    No("n");

    private String answer;

    Answer(String answer) {
        this.answer = answer;
    }

    public static Answer find(String inputAnswer) {
        validate(inputAnswer);
        return Arrays.stream(Answer.values())
            .filter(answer -> inputAnswer.equalsIgnoreCase(answer.answer))
            .findAny()
            .orElseThrow(() -> new IllegalYesOrNoInputException("y 또는 n를 입력해 주세요."));
    }

    private static void validate(String input) {
        StringUtils.validateNull(input);
        StringUtils.validateEmpty(input);
    }

    public boolean isYes() {
        return answer.equals(Answer.YES.answer);
    }
}