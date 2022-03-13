package blackjack.domain.result;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MatchResult {

    public static final int INCREASING_COUNT = 1;

    private final Map<PlayerResult, Integer> dealerResult;
    private final Map<String, PlayerResult> playerResult;


    public MatchResult(final Map<String, PlayerResult> playerResult) {
        this.dealerResult = calculateDealerResult(playerResult);
        this.playerResult = playerResult;
    }

    private Map<PlayerResult, Integer> calculateDealerResult(final Map<String, PlayerResult> playerResult) {
        final Map<PlayerResult, Integer> collectResult = new EnumMap<>(PlayerResult.class);

        for (final PlayerResult result : playerResult.values()) {
            collectResult.merge(result, INCREASING_COUNT, Integer::sum);
        }

        return makeDealerResult(collectResult);
    }

    private Map<PlayerResult, Integer> makeDealerResult(Map<PlayerResult, Integer> collectResult) {
        final Map<PlayerResult, Integer> result = new EnumMap<>(PlayerResult.class);
        result.put(PlayerResult.LOSS, collectResult.getOrDefault(PlayerResult.WIN, 0));
        result.put(PlayerResult.DRAW, collectResult.getOrDefault(PlayerResult.DRAW, 0));
        result.put(PlayerResult.WIN, collectResult.getOrDefault(PlayerResult.LOSS, 0));

        return result;
    }

    public Map<PlayerResult, Integer> getDealerResult() {
        return new EnumMap<>(dealerResult);
    }

    public Map<String, PlayerResult> getPlayerResult() {
        return new LinkedHashMap<>(playerResult);
    }
}
