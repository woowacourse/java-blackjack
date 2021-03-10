package blackjack.domain;

import java.util.Arrays;

public enum HitStay {
    HIT("Y"),
    STAY("N");

    private static final String INVALID_HIT_VALUE_ERROR_MESSAGE = "y 또는 n을 입력해 주세요.";

    private final String hitValue;

    HitStay(String hitValue) {
        this.hitValue = hitValue;
    }

    public static HitStay of(String value) {
        return Arrays.stream(HitStay.values())
            .filter(hitType -> hitType.is(value))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(INVALID_HIT_VALUE_ERROR_MESSAGE));
    }

    private boolean is(String value) {
        return this.hitValue.equals(value);
    }

    public boolean isHit() {
        return HIT.equals(this);
    }
}
