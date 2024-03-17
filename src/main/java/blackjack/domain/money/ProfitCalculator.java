package blackjack.domain.money;

import blackjack.domain.game.PlayerResult;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;

public class ProfitCalculator {

    public void playerProfit(Player player, PlayerResult playerResult) {
        Long additionalProfit = playerResult.additionalProfit(player.bettingMoney());
        player.addProfit(additionalProfit);
    }

    public void dealerProfit(Dealer dealer, Players players) {
        Long profit = players.allProfit() * -1;
        dealer.addProfit(profit);
    }
}
