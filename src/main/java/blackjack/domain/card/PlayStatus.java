package blackjack.domain.card;

import java.util.Arrays;

public enum PlayStatus {
    BUST,
    HIT,
    STAY;

    public static PlayStatus updateStatus(PlayStatus playStatus) {
        return Arrays.stream(values())
            .filter(it -> it == playStatus)
            .findAny()
            .orElseThrow();
    }
}
