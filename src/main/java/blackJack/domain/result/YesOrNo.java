package blackJack.domain.result;

import java.util.Arrays;
import java.util.Objects;

public enum YesOrNo {
    YES("y"),
    NO("n")
    ;

    private static final String ERROR_MESSAGE_YES_OR_NO = "y 또는 n을 입력해주세요.";

    private final String choice;

    YesOrNo(String choice) {
        this.choice = choice;
    }

    public static YesOrNo find(String value) {
        return Arrays.stream(YesOrNo.values())
            .filter(yesOrNo -> Objects.equals(yesOrNo.choice, value))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(ERROR_MESSAGE_YES_OR_NO));
    }
}
