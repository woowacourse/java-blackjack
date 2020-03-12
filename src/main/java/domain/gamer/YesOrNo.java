package domain.gamer;

import exception.YesOrNoFormatException;

import java.util.Arrays;

public enum YesOrNo {
    YES("y", true),
    NO("n", false);

    private String answerValue;
    private boolean drawable;

    YesOrNo(String answerValue, boolean drawable) {
        this.answerValue = answerValue;
        this.drawable = drawable;
    }

    public boolean getDrawable() {
        return drawable;
    }

    public static YesOrNo findYesOrNo(String answer) {
        return Arrays.stream(values())
                .filter(x -> x.answerValue.equals(answer.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new YesOrNoFormatException("y 또는 n을 입력해주세요."));
    }
}
