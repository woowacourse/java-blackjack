package blackjack;

import java.util.Arrays;

public enum YesOrNo {
    YES("y", true),
    NO("n", false);

    String input;
    boolean answer;

    YesOrNo(String input, boolean answer) {
        this.input = input;
        this.answer = answer;
    }

    public static YesOrNo get(String input) {
        return Arrays.stream(YesOrNo.values())
                .filter(x -> x.getInput().equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 입력입니다."));
    }

    public static boolean isYes(YesOrNo yesOrNo) {
        return yesOrNo.equals(YesOrNo.YES);
    }

    public String getInput() {
        return input;
    }
}
