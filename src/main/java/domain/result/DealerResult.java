package domain.result;

import domain.Profit;
import domain.gamer.Player;
import java.util.Map;

public class DealerResult {

    private Profit profitResult;

    public DealerResult() {
        this.profitResult = new Profit(0);
    }

    public void calculateProfit(Map<Player, Profit> playersProfit) {
        profitResult = new Profit(-playersProfit.values().stream()
                .mapToInt(Profit::getValue)
                .sum());
    }

    public Profit getProfitResult() {
        return profitResult;
    }
}
