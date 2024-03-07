package blackjack.domain;

import blackjack.domain.participant.Player;
import java.util.HashMap;
import java.util.Map;

public class BlackjackResult {
    private final Map<Player, HandResult> playersResult;

    public BlackjackResult(Map<Player, HandResult> playersResult) {
        this.playersResult = playersResult;
    }

    public Map<HandResult, Integer> getDealerResult() {
        Map<HandResult, Integer> dealerResults = new HashMap<>();
        for (HandResult playerResult : playersResult.values()) {
            HandResult dealerResult = playerResult.getOpposite();
            int currentResultCount = dealerResults.getOrDefault(dealerResult, 0);
            dealerResults.put(dealerResult, currentResultCount + 1);
        }
        return dealerResults;
    }

    public Map<Player, HandResult> getPlayersResult() {
        return playersResult;
    }
}
