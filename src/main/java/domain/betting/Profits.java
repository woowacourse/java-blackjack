package domain.betting;

import domain.gamer.PlayerName;

import java.util.HashMap;
import java.util.Map;

public class Profits {

    private static final int PREFIX_INITIAL_PROFIT = 0;

    private final Map<PlayerName, Profit> profitByPlayer;

    public Profits() {
        this.profitByPlayer = new HashMap<>();
    }

    public void settleProfit(PlayerName playerName, Profit profit) {
        profitByPlayer.put(playerName, profit);
    }

    public Profit getProfit(PlayerName playerName) {
        return profitByPlayer.get(playerName);
    }

    public Profit calculateDealerProfit() {
        return profitByPlayer.values()
                .stream()
                .reduce(new Profit(PREFIX_INITIAL_PROFIT), Profit::addProfit)
                .reverseProfit();
    }

}
