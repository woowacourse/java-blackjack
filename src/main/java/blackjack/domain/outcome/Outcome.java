package blackjack.domain.outcome;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public enum Outcome {

    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String outcome;

    Outcome(String outcome) {
        this.outcome = outcome;
    }

    public String get() {
        return outcome;
    }

    public static Map<Outcome, Integer> determineDealerOutcome(Map<Player, Outcome> playerOutcomes) {
        Map<Outcome, Integer> dealerOutcome = new LinkedHashMap<>();
        Collection<Outcome> outcomes = playerOutcomes.values();
        dealerOutcome.put(WIN, Collections.frequency(outcomes, LOSE));
        dealerOutcome.put(DRAW, Collections.frequency(outcomes, DRAW));
        dealerOutcome.put(LOSE, Collections.frequency(outcomes, WIN));

        return dealerOutcome;
    }

    public static Outcome determinePlayerOutcome(Dealer dealer, Player player) {
        if (dealer.isBust() || player.isBust()) {
            return checkBust(dealer, player);
        }
        if (dealer.isBlackjack() || player.isBlackjack()) {
            return checkBlackjack(dealer, player);
        }
        return checkScore(dealer, player);
    }

    private static Outcome checkBust(Dealer dealer, Player player) {
        if (dealer.isBust() && player.isBust()) {
            return Outcome.DRAW;
        }
        if (player.isBust()) {
            return Outcome.LOSE;
        }
        return Outcome.WIN;
    }

    private static Outcome checkBlackjack(Dealer dealer, Player player) {
        if (dealer.isBlackjack() && player.isBlackjack()) {
            return Outcome.DRAW;
        }
        if (player.isBlackjack()) {
            return Outcome.WIN;
        }
        return Outcome.LOSE;
    }

    private static Outcome checkScore(Dealer dealer, Player player) {
        int gap = player.getScore() - dealer.getScore();
        if (gap > 0) {
            return Outcome.WIN;
        }
        if (gap < 0) {
            return Outcome.LOSE;
        }
        return Outcome.DRAW;
    }

}
