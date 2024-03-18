package blackjack.dto;

import blackjack.model.betting.Betting;
import blackjack.model.dealer.Dealer;
import blackjack.model.player.Player;
import blackjack.vo.PlayerName;

public record PlayerBettingProfitOutcome(PlayerName name, int profit) {
    public static PlayerBettingProfitOutcome from(final Player player, final Betting betting, final Dealer dealer) {
        return new PlayerBettingProfitOutcome(player.getName(), player.calculateBettingProfit(betting, dealer));
    }
}
