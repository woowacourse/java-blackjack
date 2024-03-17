package blackjack.dto;

import blackjack.model.betting.Betting;
import blackjack.model.dealer.Dealer;
import blackjack.model.player.Player;
import blackjack.model.player.PlayerName;

public record PlayerBettingProfitOutcome(PlayerName name, int profit) {
    public static PlayerBettingProfitOutcome from(Player player, Betting betting, Dealer dealer) {
        return new PlayerBettingProfitOutcome(player.getName(), player.calculateBettingProfit(betting, dealer));
    }
}
