package blackjack.domain.result;

import blackjack.domain.participant.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class BlackjackResult {
    private final Map<Player, HandResult> playersResult;

    public BlackjackResult(Map<Player, HandResult> playersResult) {
        this.playersResult = playersResult;
    }

    public Map<HandResult, Integer> getDealerResults() {
        Map<HandResult, Integer> dealerResults = initializeDealerResults();
        for (HandResult playerResult : playersResult.values()) {
            HandResult dealerResult = playerResult.getOpposite();
            int currentResultCount = dealerResults.get(dealerResult);
            dealerResults.put(dealerResult, currentResultCount + 1);
        }
        return dealerResults;
    }

    private Map<HandResult, Integer> initializeDealerResults() {
        Map<HandResult, Integer> dealerResults = new LinkedHashMap<>();
        for (HandResult handResult : HandResult.values()) {
            dealerResults.put(handResult, 0);
        }
        return dealerResults;
    }

    public Map<Player, HandResult> getPlayersResult() {
        return playersResult;
    }
}
