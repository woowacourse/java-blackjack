package blackjack.domain.rule;

import blackjack.domain.gamer.BettingMoney;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.result.BlackJackResult;
import blackjack.domain.result.GamerProfitTable;
import blackjack.domain.result.Profit;

import java.util.LinkedHashMap;
import java.util.Map;

public class BettingTable {

    private Map<Player, BettingMoney> bettingMoneyTable;

    public BettingTable(Map<Player, BettingMoney> bettingMoneyTable) {
        this.bettingMoneyTable = bettingMoneyTable;
    }

    public GamerProfitTable calculateProfitResult(Players players, Dealer dealer) {
        Map<Gamer, Profit> gamerProfitTable = new LinkedHashMap<>();
        Profit dealerProfit = Profit.from(0);
        for (Player player : players) {
            BlackJackResult result = ResultMatcher.match(player, dealer);
            BettingMoney bettingMoney = bettingMoneyTable.get(player);
            Profit playerProfit = result.profit(bettingMoney);
            gamerProfitTable.put(player, playerProfit);
            dealerProfit = dealerProfit.minus(playerProfit);
        }
        gamerProfitTable.put(dealer, dealerProfit);
        return GamerProfitTable.from(gamerProfitTable);
    }
}
