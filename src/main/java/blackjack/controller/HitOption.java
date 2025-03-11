package blackjack.controller;

import java.util.Arrays;

public enum HitOption {
    YES("y"),
    NO("n");

    private static final String INVALID_OPTION = "존재하지 않는 옵션입니다.";
    private final String option;

    HitOption(String option) {
        this.option = option;
    }

    public static HitOption from(String option) {
        return Arrays.stream(HitOption.values())
                .filter(hitOption -> hitOption.option.equals(option))
                .findAny()
                .orElseThrow(() -> new IllegalStateException(INVALID_OPTION));
    }

    public boolean isYes() {
        return this == YES;
    }
}
