package machine;

import java.util.Arrays;

public enum HitStay {

    HIT("y"),
    STAY("n");

    private final String input;

    HitStay(String input) {
        this.input = input;
    }

    public static HitStay from(String input) {
        return Arrays.stream(values())
            .filter(value -> value.input.equals(input))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 적절하지 않은 입력입니다."));
    }
}
