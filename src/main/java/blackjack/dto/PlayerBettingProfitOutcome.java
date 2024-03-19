package blackjack.dto;

import blackjack.model.betting.Betting;
import blackjack.model.dealer.Dealer;
import blackjack.model.player.Player;

public record PlayerBettingProfitOutcome(String name, int profit) {
    public static PlayerBettingProfitOutcome from(final Player player, final Betting betting, final Dealer dealer) {
        return new PlayerBettingProfitOutcome(
                player.getName().name(),
                player.calculateBettingProfit(betting, dealer)
        );
    }
}
