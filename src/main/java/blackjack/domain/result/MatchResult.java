package blackjack.domain.result;

import java.util.EnumMap;
import java.util.Map;

public class MatchResult {

    public static final int INCREASING_COUNT = 1;

    private final Map<WinningResult, Integer> dealerResult;
    private final Map<String, WinningResult> playerResult;


    public MatchResult(final Map<String, WinningResult> playerResult) {
        this.dealerResult = calculateDealerResult(playerResult);
        this.playerResult = playerResult;
    }

    private Map<WinningResult, Integer> calculateDealerResult(final Map<String, WinningResult> playerResult) {
        final Map<WinningResult, Integer> collectResult = new EnumMap<>(WinningResult.class);

        for (final WinningResult result : playerResult.values()) {
            collectResult.merge(result, INCREASING_COUNT, Integer::sum);
        }

        final Map<WinningResult, Integer> dealerResult = new EnumMap<>(WinningResult.class);
        dealerResult.put(WinningResult.WIN, collectResult.getOrDefault(WinningResult.LOSS, 0));
        dealerResult.put(WinningResult.LOSS, collectResult.getOrDefault(WinningResult.WIN, 0));
        return dealerResult;
    }

    public Map<WinningResult, Integer> getDealerResult() {
        return Map.copyOf(dealerResult);
    }

    public Map<String, WinningResult> getPlayerResult() {
        return Map.copyOf(playerResult);
    }
}
