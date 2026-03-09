package view;

import java.util.Arrays;

public enum SelectFormat {

    POSITIVE("y"),
    NEGATIVE("n"),
    ;

    private final String sign;

    SelectFormat(String sign) {
        this.sign = sign;
    }

    public String sign() {
        return sign;
    }

    public static boolean isCorrect(String input) {
        return Arrays.stream(SelectFormat.values())
                .anyMatch(format -> format.sign.equals(input));
    }
}
