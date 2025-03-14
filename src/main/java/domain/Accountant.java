package domain;

import static domain.WinLossResult.computeWinLoss;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Accountant {
    Map<Player, Integer> bettingPrices;

    public Accountant() {
        bettingPrices = new HashMap<Player, Integer>();
    }

    public void accountBettingPrice(Player player, int price) {
        bettingPrices.put(player, price);
    }

    public Map<Player, Integer> calculateProfit(Players players, Dealer dealer) {
        Map<Player, Integer> profitPerParticipant = new LinkedHashMap<>();
        int dealersProfit = 0;
        for (Player player : players.getPlayers()) {
            int playersProfit = getProfit(player, computeWinLoss(player, dealer));
            profitPerParticipant.put(player, playersProfit);
            dealersProfit -= playersProfit;
        }
        profitPerParticipant.put(dealer, dealersProfit);
        return profitPerParticipant;
    }

    public int getBettingPrice(Player player) {
        return bettingPrices.get(player);
    }

    public int getProfit(Player player, WinLossResult winLossResult) {
        int bettingPrice = getBettingPrice(player);
        if (winLossResult == WinLossResult.WIN_WITH_BLACK_JACK) {
            return bettingPrice / 2;
        }
        if (winLossResult == WinLossResult.WIN) {
            return bettingPrice;
        }
        if (winLossResult == WinLossResult.DRAW) {
            return 0;
        }
        if (winLossResult == WinLossResult.LOSS) {
            return bettingPrice * -1;
        }
        throw new IllegalArgumentException("Invalid win loss result: " + winLossResult);
    }
}
