package blackjack.game.betting;

import blackjack.game.GameResult;
import blackjack.user.Dealer;
import blackjack.user.Player;
import blackjack.user.PlayerName;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Betting {

    private final Map<PlayerName, BetAmount> bettingTable;

    public Betting(Map<PlayerName, BetAmount> bettingTable) {
        this.bettingTable = bettingTable;
    }

    public static Betting createWithEmptyTable() {
        return new Betting(new LinkedHashMap<>());
    }

    public void calculateProfitForPlayer(final Dealer dealer, final List<Player> players) {
        for(Player player : players) {
            GameResult gameResult = dealer.judgePlayerResult(player);

            PlayerName name = player.getName();
            BetAmount newBetAmount = bettingTable.get(name).calculateProfit(gameResult, player.getCards().isBlackjack());
            bettingTable.put(name, newBetAmount);
        }
    }

    public Map<PlayerName, BetAmount> getBettingTable() {
        return bettingTable;
    }
}
