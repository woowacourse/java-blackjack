package blackjack.view.expressions;

import java.util.Arrays;

public enum HitStandExpressions {
    HIT("y", true),
    STAND("n", false),
    ;

    private final String value;
    private final boolean isHit;

    HitStandExpressions(String value, boolean isHit) {
        this.value = value;
        this.isHit = isHit;
    }

    public static boolean mapHitStandStringToBoolean(final String inputMessage) {
        return Arrays.stream(values())
                .filter(hitStandExpression -> hitStandExpression.value.equals(inputMessage))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("[ERROR] %s 또는 %s 만 입력할 수 있습니다.", HIT.value, STAND.value)))
                .isHit;
    }

    public String getValue() {
        return value;
    }
}
