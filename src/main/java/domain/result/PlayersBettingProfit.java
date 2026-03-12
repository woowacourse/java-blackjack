package domain.result;

import domain.pariticipant.Player;

import java.util.Map;

public record PlayersBettingProfit(Map<Player, Integer> playersBettingProfit) {

    @Override
    public Map<Player, Integer> playersBettingProfit() {
        return Map.copyOf(playersBettingProfit);
    }

    public int calculateDealerProfit() {
        int dealerProfit = 0;
        for (Player player : playersBettingProfit.keySet()) {
            dealerProfit += playersBettingProfit.get(player) * -1;
        }

        return dealerProfit;
    }
}
