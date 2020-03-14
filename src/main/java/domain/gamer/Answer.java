package domain.gamer;

import exception.YesOrNoFormatException;

import java.util.Arrays;

public enum Answer {
    YES("y", true),
    NO("n", false);

    private String answerValue;
    private boolean drawable;

    Answer(String answerValue, boolean drawable) {
        this.answerValue = answerValue;
        this.drawable = drawable;
    }

    public boolean getDrawable() {
        return drawable;
    }

    public static Answer findAnswer(String answer) {
        return Arrays.stream(values())
                .filter(x -> x.answerValue.equals(answer.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new YesOrNoFormatException("y 또는 n을 입력해주세요."));
    }
}
