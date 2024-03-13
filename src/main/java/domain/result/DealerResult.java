package domain.result;

import domain.Profit;
import domain.WinState;
import domain.gamer.Player;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class DealerResult {

    private final Map<WinState, Integer> result;
    private Profit profitResult;

    public DealerResult() {
        this.result = new LinkedHashMap<>();
        this.profitResult = new Profit(0);
    }

    public void addResult(WinState winState, int winStateCount) {
        result.put(winState, winStateCount);
    }

    public void calculateProfit(Map<Player, Profit> playersProfit) {
        profitResult = new Profit(-playersProfit.values().stream()
                .mapToInt(Profit::getValue)
                .sum());
    }

    public Map<WinState, Integer> getResult() {
        return Collections.unmodifiableMap(result);
    }

    public Profit getProfitResult() {
        return profitResult;
    }
}
