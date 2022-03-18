package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProfitResult {

    public static final int NEGATIVE_ONE = -1;

    private double dealerProfit = 0;
    private final Map<Participant, Double> playersProfit = new LinkedHashMap<>();

    public void putPlayerProfit(Participant participant, double profit) {
        playersProfit.put(participant, profit);
        dealerProfit += profit * NEGATIVE_ONE;
    }

    public double getDealerProfit() {
        return dealerProfit;
    }

    public Map<Participant, Double> getPlayersProfit() {
        return playersProfit;
    }
}
