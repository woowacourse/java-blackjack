package blackjack.domain.user;

import java.util.Arrays;

public enum HitOrStay {

    HIT("y"),
    STAY("n");

    private String input;
    private boolean toHit;

    HitOrStay (String input) {
        this.input = input;
    }

    public static HitOrStay of(String input) {
        return Arrays.stream(values())
                .filter(hitOrStay -> hitOrStay.input.equals(input))
                .findFirst()
                .orElseThrow(HitOrStayException::new);
    }

    public boolean isToHit() {
        return toHit;
    }
}
