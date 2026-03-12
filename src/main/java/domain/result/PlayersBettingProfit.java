package domain.result;

import domain.pariticipant.Player;

import java.util.Map;

public record PlayersBettingProfit(Map<Player, Long> playersBettingProfit) {

    @Override
    public Map<Player, Long> playersBettingProfit() {
        return Map.copyOf(playersBettingProfit);
    }

    public long calculateDealerProfit() {
        long dealerProfit = 0;
        for (Player player : playersBettingProfit.keySet()) {
            dealerProfit += playersBettingProfit.get(player) * -1;
        }

        return dealerProfit;
    }
}
