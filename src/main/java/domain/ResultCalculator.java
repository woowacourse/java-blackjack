package domain;

import java.util.HashMap;
import java.util.Map;

public class ResultCalculator {
    public GameResult calculate(Dealer dealer, Players players) {
        final int dealerScore = dealer.getResult();
        final boolean dealerBust = dealer.checkBust();
        final Map<String, Outcome> playerOutcomes = new HashMap<>();
        final Map<Outcome, Integer> dealerOutcomeCounts = initOutcomeCounts();

        players.forEachPlayer(player -> {
            final int playerScore = player.getResult();
            final boolean playerBust = player.checkBust();
            final Outcome playerOutcome = decidePlayerOutcome(dealerScore, dealerBust, playerScore, playerBust);
            final Outcome dealerOutcome = reverse(playerOutcome);
            playerOutcomes.put(player.getName(), playerOutcome);
            dealerOutcomeCounts.put(dealerOutcome, dealerOutcomeCounts.get(dealerOutcome) + 1);
        });
        return new GameResult(playerOutcomes, dealerOutcomeCounts);
    }

    private Map<Outcome, Integer> initOutcomeCounts() {
        final Map<Outcome, Integer> dealerOutcomeCounts = new HashMap<>();
        for (Outcome outcome : Outcome.values()) {
            dealerOutcomeCounts.put(outcome, 0);
        }
        return dealerOutcomeCounts;
    }

    private Outcome decidePlayerOutcome(int dealerScore, boolean dealerBust, int playerScore, boolean playerBust) {
        if (playerBust) {
            return Outcome.LOSE;
        }
        if (dealerBust) {
            return Outcome.WIN;
        }
        if (dealerScore >= playerScore) {
            return Outcome.LOSE;
        }
        return Outcome.WIN;
    }

    private Outcome reverse(Outcome playerOutcome) {
        if (playerOutcome == Outcome.WIN) {
            return Outcome.LOSE;
        }
        return Outcome.WIN;
    }
}
