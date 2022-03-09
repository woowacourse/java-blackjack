package blackjack.domain;

import java.util.Arrays;

public enum WinningResult {

    WIN(true),
    LOSS(false);

    private final boolean result;

    WinningResult(boolean result) {
        this.result = result;
    }

    public static WinningResult valueOf(boolean result) {
        return Arrays.stream(WinningResult.values())
                .filter(it -> it.result == result)
                .findAny()
                .get();
    }
}
