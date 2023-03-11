package domain.game;

import domain.money.BettingAmount;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Users;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameResult {

    public static final int DEALER_PROFIT_RATE = -1;

    private final Map<String, Integer> playerProfits;
    private final int dealerProfit;

    private GameResult(final Map<String, Integer> playerProfits,
        final int dealerProfit) {
        this.playerProfits = playerProfits;
        this.dealerProfit = dealerProfit;
    }

    public static GameResult of(final Users users) {
        Map<String, Integer> playerProfits = calculatePlayerProfits(users);
        int dealerResult = calculateDealerProfit(playerProfits);
        return new GameResult(playerProfits, dealerResult);
    }

    private static Map<String, Integer> calculatePlayerProfits(final Users users) {
        List<Player> players = users.getPlayers();
        Dealer dealer = users.getDealer();
        Map<String, Integer> playerProfits = new LinkedHashMap<>();
        for (Player player : players) {
            int profit = calculateEachPlayerProfit(player, dealer);
            playerProfits.put(player.getName(), profit);
        }
        return Collections.unmodifiableMap(playerProfits);
    }

    private static int calculateEachPlayerProfit(final Player player, final Dealer dealer) {
        BettingAmount bettingAmount = player.getBettingAmount();
        Winning winning = player.match(dealer);
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
