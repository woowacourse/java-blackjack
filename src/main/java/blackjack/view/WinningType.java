package blackjack.view;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

import blackjack.domain.betting.BettingAmount;
import java.util.Arrays;
import java.util.Map;

public enum WinningType {
    BLACKJACK_WIN("블랙잭 승", 1.5),
    BLACKJACK_DRAW("블랙잭 무승부", 1.0),
    WIN("승", 2.0),
    DEFEAT("패", 0),
    DRAW("무", 1.0);

    private final String displayName;
    private final double winningRate;

    WinningType(final String displayName, final double winningRate) {
        this.displayName = displayName;
        this.winningRate = winningRate;
    }

    public int calculateWinningAmount(final BettingAmount amount) {
        return amount.multiplyRate(winningRate);
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Map<WinningType, Integer> createWinningResult() {
        return Arrays.stream(WinningType.values())
                .collect(toMap(identity(), type -> 0));
    }
}
