package domain.result;

import domain.participant.Player;
import java.util.EnumMap;
import java.util.Map;

public class GameResult {
    private final Map<Player, Outcome> playerResults;

    public GameResult(final Map<Player, Outcome> playerResults) {
        this.playerResults = playerResults;
    }

    public Map<Outcome, Integer> getDealerResult() {
        Map<Outcome, Integer> dealerResult = new EnumMap<>(Outcome.class);
        for (Outcome outcome : Outcome.values()) {
            dealerResult.put(outcome, 0);
        }
        for (Outcome playerOutcome : playerResults.values()) {
            Outcome dealerOutcome = playerOutcome.opposite();
            dealerResult.put(dealerOutcome, dealerResult.get(dealerOutcome) + 1);
        }
        return dealerResult;
    }
}
