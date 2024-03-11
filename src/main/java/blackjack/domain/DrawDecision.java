package blackjack.domain;

import java.util.Arrays;

public enum DrawDecision {

    YES("y"),
    NO("n");

    private final String code;

    DrawDecision(String code) {
        this.code = code;
    }

    public static DrawDecision from(String code) {
        return Arrays.stream(values())
                .filter(drawDecision -> drawDecision.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] " + YES.code + "또는 " + NO.code + "로 입력해주세요"));
    }
}
