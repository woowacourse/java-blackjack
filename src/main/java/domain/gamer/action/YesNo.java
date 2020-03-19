package domain.gamer.action;

import java.util.Arrays;

public enum YesNo {
    YES("y"),
    NO("n");

    private final String value;

    YesNo(String value) {
        this.value = value;
    }

    public static YesNo of(String input) {
        return Arrays.stream(YesNo.values())
                .filter(eachYesNo -> input.equals(eachYesNo.value))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("y와 n만 가능합니다."));
    }

    public boolean isYes() {
        return this == YES;
    }

    public boolean isNo() {
        return this == NO;
    }
}
