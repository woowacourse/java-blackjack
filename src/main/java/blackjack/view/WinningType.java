package blackjack.view;

import static java.util.function.Function.identity;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum WinningType {
    WIN("승"),
    DEFEAT("패"),
    DRAW("무");

    private final String displayName;

    WinningType(final String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Map<WinningType, Integer> createWinningResult() {
        return Arrays.stream(WinningType.values())
                .collect(Collectors.toMap(identity(), type -> 0));
    }
}
