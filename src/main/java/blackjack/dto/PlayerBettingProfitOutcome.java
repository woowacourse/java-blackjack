package blackjack.dto;

import blackjack.model.betting.Betting;
import blackjack.model.dealer.Dealer;
import blackjack.model.player.Player;

public record PlayerBettingProfitOutcome(String name, int profit) {
    public static PlayerBettingProfitOutcome from(Player player, Betting betting, Dealer dealer) {
        return new PlayerBettingProfitOutcome(player.getName(), player.calculateBettingProfit(betting, dealer));
    }
}
