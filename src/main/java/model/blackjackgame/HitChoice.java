package model.blackjackgame;

import java.util.Arrays;

public enum HitChoice {
    YES("y"),
    NO("n");

    private final String displayName;

    HitChoice(String displayName) {
        this.displayName = displayName;
    }

    public static HitChoice findHitChoice(String choice) {
        return Arrays.stream(values())
            .filter(hitChoice -> hitChoice.displayName.equals(choice))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("y 혹은 n 중 하나를 입력해 주세요."));
    }

    public boolean isHit() {
        return this == YES;
    }
}
