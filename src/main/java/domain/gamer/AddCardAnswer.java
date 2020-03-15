package domain.gamer;

import exception.YesOrNoFormatException;

import java.util.Arrays;

public enum AddCardAnswer {
    YES("y", true),
    NO("n", false);

    private String answerValue;
    private boolean drawable;

    AddCardAnswer(String answerValue, boolean drawable) {
        this.answerValue = answerValue;
        this.drawable = drawable;
    }

    public static boolean isYes(String answer) {
        return Arrays.stream(values())
                .filter(x -> x.answerValue.equals(answer.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new YesOrNoFormatException("y 또는 n을 입력해주세요."))
                .drawable;
    }
}
