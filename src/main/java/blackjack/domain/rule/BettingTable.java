package blackjack.domain.rule;

import blackjack.domain.gamer.BettingMoney;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.gamer.Profit;
import blackjack.domain.result.BlackJackResult;

import java.util.LinkedHashMap;
import java.util.Map;

public class BettingTable {

    private Map<Player, BettingMoney> bettingMoneyTable;

    public BettingTable(Map<Player, BettingMoney> bettingMoneyTable) {
        this.bettingMoneyTable = bettingMoneyTable;
    }

    public Map<Gamer, Profit> calculateBettingResult(Players players, Dealer dealer) {
        Map<Gamer, Profit> gamerProfitTable = new LinkedHashMap<>();
        double dealerProfit = 0;
        for (Player player : players) {
            BlackJackResult result = ResultMatcher.match(player, dealer);
            BettingMoney bettingMoney = bettingMoneyTable.get(player);
            Profit playerProfit = Profit.of(bettingMoney.getBettingMoney(), result.getProfitRate());
            gamerProfitTable.put(player, playerProfit);
            dealerProfit -= playerProfit.getProfit();
        }
        gamerProfitTable.put(dealer, Profit.from(dealerProfit));
        return gamerProfitTable;
    }
}
