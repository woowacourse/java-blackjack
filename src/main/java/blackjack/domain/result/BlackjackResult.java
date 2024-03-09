package blackjack.domain.result;

import blackjack.domain.participant.Player;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlackjackResult {
    private final Map<Player, HandResult> playersResult;

    public BlackjackResult(Map<Player, HandResult> playersResult) {
        this.playersResult = Collections.unmodifiableMap(playersResult);
    }

    public Map<HandResult, Integer> getDealerResults() {
        Map<HandResult, Integer> dealerResults = initializeDealerResults();
        for (HandResult playerResult : playersResult.values()) {
            HandResult dealerResult = playerResult.getOpposite();
            dealerResults.computeIfPresent(dealerResult, (handResult, count) -> count + 1);
        }
        return dealerResults;
    }

    public Map<Player, HandResult> getPlayersResult() {
        return playersResult;
    }

    private Map<HandResult, Integer> initializeDealerResults() {
        Map<HandResult, Integer> dealerResults = new LinkedHashMap<>();
        for (HandResult handResult : HandResult.values()) {
            dealerResults.put(handResult, 0);
        }
        return dealerResults;
    }
}
