package blackjack.domain.rule;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.result.BlackJackResult;

import java.util.LinkedHashMap;
import java.util.Map;

public class BettingTable {

    private Map<Player, Integer> bettingMoneyTable;

    public BettingTable(Map<Player, Integer> bettingMoneyTable) {
        this.bettingMoneyTable = bettingMoneyTable;
    }

    public Map<Gamer, Integer> calculateBettingMoney(Players players, Dealer dealer) {
        Map<Gamer, Integer> gamerProfitTable = new LinkedHashMap<>();
        gamerProfitTable.put(dealer, 0);
        for (Player player : players) {
            BlackJackResult result = ResultMatcher.match(player, dealer);
            int playerProfit = (int) (bettingMoneyTable.get(player) * result.getProfitRate());
            gamerProfitTable.put(player, playerProfit);
            gamerProfitTable.computeIfPresent(dealer, (key, value) -> value -= playerProfit);
        }
        return gamerProfitTable;
    }
}
