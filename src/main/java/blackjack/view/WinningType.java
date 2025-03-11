package blackjack.view;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.Map;

public enum WinningType {
    BLACKJACK_WIN("블랙잭 승", 1.5),
    WIN("승", 2.0),
    DEFEAT("패", -1.0),
    DRAW("무", 1.0);

    private final String displayName;
    private final double profitRate;

    WinningType(final String displayName, final double profitRate) {
        this.displayName = displayName;
        this.profitRate = profitRate;
    }

    public int multiplyProfitRate(final int amount) {
        return (int) (amount * profitRate);
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Map<WinningType, Integer> createWinningResult() {
        return Arrays.stream(WinningType.values())
                .collect(toMap(identity(), type -> 0));
    }
}
