package domain;

import java.util.HashMap;
import java.util.Map;

public class ResultCalculator {
    public GameResult calculate(Dealer dealer, Players players) {
        final int dealerResultScore = toResultScore(dealer.getResult(), dealer.checkBust());
        final Map<String, Outcome> playerOutcomes = new HashMap<>();
        final Map<Outcome, Integer> dealerOutcomeCounts = initOutcomeCounts();

        players.forEachPlayer(player -> {
            final int playerResultScore = toResultScore(player.getResult(), player.checkBust());
            final Outcome playerOutcome = decidePlayerOutcome(dealerResultScore, playerResultScore);
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

    private int toResultScore(int score, boolean bust) {
        if (bust) {
            return -1;
        }
        return score;
    }

    private Outcome decidePlayerOutcome(int dealerResultScore, int playerResultScore) {
        if (dealerResultScore == playerResultScore) {
            return Outcome.DRAW;
        }
        if (dealerResultScore > playerResultScore) {
            return Outcome.LOSE;
        }
        return Outcome.WIN;
    }

    private Outcome reverse(Outcome playerOutcome) {
        if (playerOutcome == Outcome.DRAW) {
            return Outcome.DRAW;
        }
        if (playerOutcome == Outcome.WIN) {
            return Outcome.LOSE;
        }
        return Outcome.WIN;
    }
}
