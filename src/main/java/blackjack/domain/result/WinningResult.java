package blackjack.domain.result;

import java.util.Arrays;

public enum WinningResult {

    WIN("승", true),
    LOSS("패", false);

    private final String name;
    private final boolean result;

    WinningResult(final String name, final boolean result) {
        this.name = name;
        this.result = result;
    }

    public static WinningResult valueOf(boolean result) {
        return Arrays.stream(WinningResult.values())
                .filter(it -> it.result == result)
                .findAny()
                .get();
    }

    public String getName() {
        return name;
    }
}
