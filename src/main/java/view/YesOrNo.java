package view;

import java.util.Arrays;
import java.util.Objects;

public enum YesOrNo {
    YES("y"),
    NO("n"),
    ;
    private final String input;

    YesOrNo(String input) {
        this.input = input;
    }

    public static YesOrNo from(String input) {
        return Arrays.stream(values())
                .filter(yesOrNo -> Objects.equals(yesOrNo.input, input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("y 또는 n만 입력해주세요."));
    }
}
