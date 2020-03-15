package model;

import exception.IllegalYesOrNoInputException;
import utils.StringUtils;

import java.util.Arrays;

public enum YesOrNo {
    YES("y", true),
    NO("n", false);

    private String yesOrNo;
    private boolean trueOrFalse;

    YesOrNo(String yesOrNo, boolean trueOrFalse) {
        this.yesOrNo = yesOrNo;
        this.trueOrFalse = trueOrFalse;
    }

    public static YesOrNo getYesOrNoByValue(String input){
        validate(input);
        return Arrays.stream(YesOrNo.values())
                .filter(yesOrNo1 -> yesOrNo1.isSameYesOrNo(input))
                .findAny()
                .orElseThrow(()->new IllegalYesOrNoInputException("Y 또는 N를 입력해 주세요."));
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

