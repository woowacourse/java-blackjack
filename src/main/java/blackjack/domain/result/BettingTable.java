package blackjack.domain.result;

import blackjack.controller.dto.GamersResultDto;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.money.Profit;

import java.util.LinkedHashMap;
import java.util.Map;

public class BettingTable {

    public GamersResultDto calculateProfit(Dealer dealer, Players players) {
        Map<Gamer, Profit> gamersProfit = new LinkedHashMap<>();
        int dealerProfit = 0;

        for (Player player : players) {
            int playerProfit = (int) (player.getBettingMoney() * BlackJackResult.findResult(dealer, player).getProfitRate());
            dealerProfit -= playerProfit;

            gamersProfit.put(player, new Profit(playerProfit));
        }
        gamersProfit.put(dealer, new Profit(dealerProfit));

        return new GamersResultDto(gamersProfit);
    }
}