package blackjack.model.referee;

import blackjack.model.dealer.Dealer;
import blackjack.model.player.Player;
import blackjack.view.dto.PlayerOutcome;

public class Referee {
    public PlayerOutcome determinePlayerOutcome(final Player player, final Dealer dealer) {
        return new PlayerOutcome(player.getName(), determineOutcome(player, dealer));
    }

    public Outcome determineOutcome(final Player player, final Dealer dealer) {
        int playerTotal = player.calculateCardsTotal();
        int dealerTotal = dealer.calculateCardsTotal();

        if (dealer.isBurst()) {
            return Outcome.WIN;
        }
        if (player.isBurst()) {
            return Outcome.LOSE;
        }
        if (playerTotal >= dealerTotal) {
            return Outcome.WIN;
        }
        return Outcome.LOSE;
    }
}
