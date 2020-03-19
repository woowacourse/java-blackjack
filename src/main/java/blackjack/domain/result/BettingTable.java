package blackjack.domain.result;

import blackjack.controller.dto.GamersResultDto;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class BettingTable {

    private Map<Player, Integer> bettingMoneyTable;

    private BettingTable(Map<Player, Integer> bettingMoneyTable) {
        this.bettingMoneyTable = Collections.unmodifiableMap(bettingMoneyTable);
    }

    public static BettingTable of(Players players, Map<String, String> playersBettingMoney) {
        Map<Player, Integer> bettingMoneyTable = new LinkedHashMap<>();

        for (Map.Entry<String, String> entry : playersBettingMoney.entrySet()) {
            Player player = players.findPlayer(entry.getKey());
            int money = Integer.parseInt(entry.getValue());
            bettingMoneyTable.put(player, money);
        }

        return new BettingTable(bettingMoneyTable);
    }

    public GamersResultDto calculateProfit(Dealer dealer, Players players) {
        Map<Gamer, Integer> gamersProfit = new LinkedHashMap<>();
        int dealerProfit = 0;

        for (Player player : players) {
            BlackJackResult result = PlayerResultMatcher.match(dealer, player);
            int playerProfit = (int) (result.getProfitRate() * bettingMoneyTable.get(player));
            dealerProfit -= playerProfit;
            gamersProfit.put(player, playerProfit);
        }
        gamersProfit.put(dealer, dealerProfit);
        return new GamersResultDto(gamersProfit);
    }
}