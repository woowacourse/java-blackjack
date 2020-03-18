package model;

import exception.IllegalYesOrNoInputException;
import utils.StringUtils;

import java.util.Arrays;

public enum Answer {
    YES("y", true),
    NO("n", false);

    private String yesOrNo;
    private boolean trueOrFalse;

    Answer(String yesOrNo, boolean trueOrFalse) {
        this.yesOrNo = yesOrNo;
        this.trueOrFalse = trueOrFalse;
    }

    public static Answer getYesOrNoByValue(String input) {
        validate(input);
        return Arrays.stream(Answer.values())
                .filter(answer -> answer.isSameYesOrNo(input))
                .findAny()
                .orElseThrow(() -> new IllegalYesOrNoInputException(String.format("%s는 올바르지 않은 값입니다.", input)));
    }

    private boolean isSameYesOrNo(final String input) {
        return yesOrNo.equalsIgnoreCase(input);
    }

    private static void validate(String input) {
        StringUtils.validateNull(input);
        StringUtils.validateEmpty(input);
    }

    public boolean getTrueOrFalse() {
        return trueOrFalse;
    }
}

