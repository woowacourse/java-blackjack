package domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class YesOrNo {

    private static final List<String> ANSWER = Collections.unmodifiableList(Arrays.asList("y", "Y", "n", "N"));
    private static final String INPUT_ERROR_MESSAGE = "입력은 y(Y), n(N)만 가능합니다.";

    private final String answer;

    private YesOrNo(String answer) {
        validate(answer);
        this.answer = answer.toLowerCase();
    }

    public static YesOrNo of(String answer) {
        return new YesOrNo(answer);
    }

    private void validate(String answer) {
        if (!ANSWER.contains(answer)) {
            throw new IllegalArgumentException(INPUT_ERROR_MESSAGE);
        }
    }

    public boolean isYes() {
        return "y".equals(answer);
    }
}
