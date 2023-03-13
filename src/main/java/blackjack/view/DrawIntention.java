package blackjack.view;

import java.util.Arrays;

public enum DrawIntention {

    YES("y"),

    NO("n");

    private static final String PLAYER_INTENTION_ERROR_MESSAGE = "y 혹은 n 만 입력 가능 합니다.";

    private final String intention;

    DrawIntention(String intention) {
        this.intention = intention;
    }

    public static DrawIntention of(String intention) {
        return Arrays.asList(values()).stream()
                .filter(e -> e.intention.equals(intention))
                .findAny()
                .orElseThrow(() -> new IllegalStateException(PLAYER_INTENTION_ERROR_MESSAGE));
    }
}
