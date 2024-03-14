package domain.result;

import domain.Profit;
import domain.gamer.Player;
import java.util.Map;

public class DealerResult {

    private Profit profit;

    public DealerResult() {
        this.profit = new Profit(0);
    }

    public void calculateProfit(Map<Player, Profit> playersProfit) {
        profit = new Profit(-playersProfit.values().stream()
                .mapToInt(Profit::getValue)
                .sum());
    }

    public Profit getProfit() {
        return profit;
    }
}
