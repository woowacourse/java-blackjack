package domain;

import java.util.Map;

public class GameResult {
    private final Map<String, Outcome> playerOutcomes;
    private final Map<Outcome, Integer> dealerOutcomeCounts;

    public GameResult(Map<String, Outcome> playerOutcomes, Map<Outcome, Integer> dealerOutcomeCounts) {
        this.playerOutcomes = Map.copyOf(playerOutcomes);
        this.dealerOutcomeCounts = Map.copyOf(dealerOutcomeCounts);
    }

    public Outcome getPlayerOutcome(String playerName) {
        return playerOutcomes.get(playerName);
    }

    public int getDealerCount(Outcome outcome) {
        return dealerOutcomeCounts.getOrDefault(outcome, 0);
    }
}
