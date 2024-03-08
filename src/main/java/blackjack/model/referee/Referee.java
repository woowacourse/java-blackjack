package blackjack.model.referee;

import blackjack.model.dealer.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.Players;
import blackjack.view.dto.PlayerOutcome;
import java.util.List;

public class Referee {
    private final Dealer dealer;

    public Referee(Dealer dealer) {
        this.dealer = dealer;
    }

    public List<PlayerOutcome> determinePlayersOutcome(final Players players) {
        return players.getPlayers().stream()
                .map(this::determinePlayerOutcome)
                .toList();
    }

    private PlayerOutcome determinePlayerOutcome(final Player player) {
        return new PlayerOutcome(player.getName(), determineOutcome(player));
    }

    Outcome determineOutcome(final Player player) {
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
