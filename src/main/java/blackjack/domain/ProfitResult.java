package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProfitResult {

    public static final int NEGATIVE_ONE = -1;

    private double dealerProfit = 0;
    private final Map<String, Double> playersProfit = new LinkedHashMap<>();

    public void putPlayerProfit(String name, double profit) {
        playersProfit.put(name, profit);
        dealerProfit += profit * NEGATIVE_ONE;
    }

    public double getDealerProfit() {
        return dealerProfit;
    }

    public Map<String, Double> getPlayersProfit() {
        return playersProfit;
    }
}
