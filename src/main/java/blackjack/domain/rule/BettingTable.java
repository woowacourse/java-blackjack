package blackjack.domain.rule;

import blackjack.domain.gamer.BettingMoney;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.result.BlackJackResult;

import java.util.LinkedHashMap;
import java.util.Map;

public class BettingTable {

    private Map<Player, BettingMoney> bettingMoneyTable;

    public BettingTable(Map<Player, BettingMoney> bettingMoneyTable) {
        this.bettingMoneyTable = bettingMoneyTable;
    }

    public Map<Gamer, Integer> calculateBettingResult(Players players, Dealer dealer) {
        Map<Gamer, Integer> gamerProfitTable = new LinkedHashMap<>();
        int dealerProfit = 0;
        for (Player player : players) {
            BlackJackResult result = ResultMatcher.match(player, dealer);
            BettingMoney bettingMoney = bettingMoneyTable.get(player);
            int profit = result.profit(bettingMoney);
            gamerProfitTable.put(player, profit);
            dealerProfit -= profit;
        }
        gamerProfitTable.put(dealer, dealerProfit);
        return gamerProfitTable;
    }
}
