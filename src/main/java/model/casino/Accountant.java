package model.casino;

import static model.casino.WinLossResult.computeWinLoss;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import model.participants.Dealer;
import model.participants.Player;
import model.participants.PlayerGroup;

public class Accountant {
    Map<Player, Integer> bettingPrices;

    public Accountant() {
        bettingPrices = new HashMap<>();
    }

    public void accountBettingPrice(Player player, int price) {
        bettingPrices.put(player, price);
    }

    public Map<Player, Integer> calculateProfit(PlayerGroup playerGroup, Dealer dealer) {
        Map<Player, Integer> profitPerParticipant = new LinkedHashMap<>();
        int dealerProfit = 0;
        for (Player player : playerGroup.getPlayers()) {
            int playerProfit = getProfit(player, computeWinLoss(player, dealer));
            profitPerParticipant.put(player, playerProfit);
            dealerProfit -= playerProfit;
        }
        profitPerParticipant.put(dealer, dealerProfit);
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
            return -bettingPrice;
        }
        throw new IllegalArgumentException("Invalid win loss result: " + winLossResult);
    }
}
