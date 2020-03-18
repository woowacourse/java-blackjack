package model;

import exception.IllegalYesOrNoInputException;
import utils.StringUtils;

import java.util.Arrays;

public enum Answer {
    YES("y"),
    NO("n");

    private final String answer;

    Answer(String answer) {
        this.answer = answer;
    }

    public static Answer getYesOrNoByValue(String input) {
        StringUtils.validateString(input);
        return Arrays.stream(Answer.values())
                .filter(answer -> answer.isSameAnswer(input))
                .findAny()
                .orElseThrow(() -> new IllegalYesOrNoInputException(String.format("%s는 올바르지 않은 값입니다.", input)));
    }

    private boolean isSameAnswer(final String input) {
        return answer.equalsIgnoreCase(input);
    }

    public boolean isYes() {
        return this == YES;
    }
}

