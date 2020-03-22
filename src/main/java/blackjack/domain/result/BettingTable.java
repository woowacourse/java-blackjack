package blackjack.domain.result;

import blackjack.controller.dto.GamersResultDto;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.money.BettingMoney;
import blackjack.domain.money.Profit;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class BettingTable {

    private Map<Player, BettingMoney> bettingMoneyTable;

    private BettingTable(Map<Player, BettingMoney> bettingMoneyTable) {
        this.bettingMoneyTable = Collections.unmodifiableMap(bettingMoneyTable);
    }

    public static BettingTable of(Map<Player, String> playersBettingMoney) {
        Map<Player, BettingMoney> bettingMoneyTable = new LinkedHashMap<>();

        for (Map.Entry<Player, String> entry : playersBettingMoney.entrySet()) {
            BettingMoney money = new BettingMoney(Integer.parseInt(entry.getValue()));
            bettingMoneyTable.put(entry.getKey(), money);
        }

        return new BettingTable(bettingMoneyTable);
    }

    public GamersResultDto calculateProfit(Dealer dealer, Players players) {
        Map<Gamer, Profit> gamersProfit = new LinkedHashMap<>();
        int dealerProfit = 0;

        for (Player player : players) {
            BlackJackResult result = BlackJackResult.findResult(dealer, player);
            int playerProfit = calculatePlayerProfit(player, result);
            dealerProfit -= playerProfit;
            gamersProfit.put(player, new Profit(playerProfit));
        }
        gamersProfit.put(dealer, new Profit(dealerProfit));
        return new GamersResultDto(gamersProfit);
    }

    private int calculatePlayerProfit(Player player, BlackJackResult result) {
        return (int) (bettingMoneyTable.get(player).getValue() * result.getProfitRate());
    }
}