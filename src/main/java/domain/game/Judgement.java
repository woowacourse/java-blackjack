package domain.game;

import domain.player.Dealer;
import domain.player.Name;
import domain.player.Player;
import domain.player.Players;
import java.util.HashMap;
import java.util.Map;

public class Judgement {
    private static final int BLACKJACK_NUMBER = 21;

    public static Map<Name, Outcome> judgeOutcome(final Dealer dealer, final Players players) {
        final Map<Name, Outcome> outcomes = new HashMap<>();

        for (final Player player : players.getPlayers()) {
            outcomes.put(player.getName(), decideOutcome(dealer, player));
        }

        return outcomes;
    }

    private static Outcome decideOutcome(final Dealer dealer, final Player player) {
        if (dealer.isBlackjack() && player.isBlackjack()) {
            return Outcome.DRAW;
        }
        if (player.isBlackjack()) {
            return Outcome.BLACKJACK;
        }
        if (dealer.isBlackjack()) {
            return Outcome.LOSE;
        }
        if (isBurst(player.score())) {
            return Outcome.LOSE;
        }
        if (isBurst(dealer.score())) {
            return Outcome.WIN;
        }
        if (dealer.score() < player.score()) {
            return Outcome.WIN;
        }
        if (dealer.score() > player.score()) {
            return Outcome.LOSE;
        }
        return Outcome.DRAW;
    }

    private static boolean isBurst(final int score) {
        return score > BLACKJACK_NUMBER;
    }
}
