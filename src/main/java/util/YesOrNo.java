package util;

import java.util.Arrays;

public enum YesOrNo {

    Y("Y"),
    N("N");

    private final String answer;

    YesOrNo(String answer) {
        this.answer = answer;
    }

    public static boolean isYes(String input) {
        YesOrNo answerInput = Arrays.stream(values())
                .filter(yesOrNo -> yesOrNo.answer.equalsIgnoreCase(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 입력입니다."));

        return answerInput == Y;
    }
}
