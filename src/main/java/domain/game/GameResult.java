package domain.game;

import domain.money.BettingAmount;
import domain.user.Player;
import domain.user.Users;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class GameResult {

    private static final int DEALER_PROFIT_RATE = -1;

    private final Map<String, Integer> playerProfits;
    private final int dealerProfit;

    private GameResult(final Map<String, Integer> playerProfits,
        final int dealerProfit) {
        this.playerProfits = playerProfits;
        this.dealerProfit = dealerProfit;
    }

    public static GameResult from(final Users users) {
        Map<String, Integer> playerProfits = calculatePlayerProfits(users);
        int dealerResult = calculateDealerProfit(playerProfits);
        return new GameResult(playerProfits, dealerResult);
    }

    private static Map<String, Integer> calculatePlayerProfits(final Users users) {
        Map<Player, Winning> matchResults = users.matchPlayers();
        Map<String, Integer> playerProfits = new LinkedHashMap<>();
        for (Entry<Player, Winning> matchResult : matchResults.entrySet()) {
            Player player = matchResult.getKey();
            Winning winning = matchResult.getValue();
            playerProfits.put(player.getName(), calculateProfit(player, winning));
        }
        return Collections.unmodifiableMap(playerProfits);
    }

    private static int calculateProfit(final Player player, final Winning winning) {
        BettingAmount bettingAmount = player.getBettingAmount();
        double profitRate = winning.getProfitRate();
        return bettingAmount.multiply(profitRate);
    }

    private static int calculateDealerProfit(final Map<String, Integer> playerProfits) {
        int sumOfPlayerProfits = 0;
        for (Integer profit : playerProfits.values()) {
            sumOfPlayerProfits += profit;
        }
        return sumOfPlayerProfits * DEALER_PROFIT_RATE;
    }

    public Map<String, Integer> getPlayerProfits() {
        return playerProfits;
    }

    public int getDealerProfit() {
        return dealerProfit;
    }
}
