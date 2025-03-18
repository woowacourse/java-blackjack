package bet;

import java.util.LinkedHashMap;
import java.util.Map;
import player.Participant;

public class BetResults {
    private final Map<Participant, BetResult> betResults;

    public BetResults() {
        this.betResults = new LinkedHashMap<>();
    }

    public void addBetResult(Participant participant, BetResult betResult) {
        betResults.put(participant, betResult);
    }

    public int calculateDealerBettingResult() {
        return - betResults.values().stream()
                .mapToInt(BetResult::getAmount)
                .sum();
    }

    public Map<Participant, BetResult> getBetResults() {
        return betResults;
    }
}
