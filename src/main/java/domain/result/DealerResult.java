package domain.result;

import domain.Profit;
import domain.gamer.Player;
import java.util.Map;

public class DealerResult {

    private final Profit profit;

    public DealerResult() {
        this.profit = new Profit(0);
    }

    public void calculateProfit(PlayersResult playersResult) {
        Map<Player, Profit> playersProfit = playersResult.getPlayersProfit();
        double playersProfitSum = playersProfit.values().stream()
                .mapToDouble(Profit::getValue)
                .sum();
        profit.update(-playersProfitSum);
    }

    public Profit getProfit() {
        return profit;
    }
}
