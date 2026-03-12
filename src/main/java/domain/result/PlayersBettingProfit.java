package domain.result;

import domain.pariticipant.Player;

import java.util.Map;

public record PlayersBettingProfit(Map<Player, Long> playersBettingProfit) {

    public PlayersBettingProfit {
        playersBettingProfit = Map.copyOf(playersBettingProfit);
    }

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
