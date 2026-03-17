package domain.game;

import domain.participant.Dealer;
import domain.state.HandState;
import domain.state.Outcome;
import domain.participant.Players;
import java.util.HashMap;
import java.util.Map;

public class ResultCalculator {
    public GameResult calculate(Dealer dealer, Players players) {
        final int dealerScore = dealer.getScore();
        final HandState dealerState = dealer.getHandState();
        final Map<String, Outcome> playerOutcomes = new HashMap<>();
        final Map<Outcome, Integer> dealerOutcomeCounts = initOutcomeCounts();

        players.forEachPlayer(player -> {
            final int playerScore = player.getScore();
            final HandState playerState = player.getHandState();
            final Outcome playerOutcome = playerState.against(dealerState, playerScore, dealerScore);
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
