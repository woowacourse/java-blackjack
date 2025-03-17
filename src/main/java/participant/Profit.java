package participant;

import java.util.Map;

public class Profit {

    private final Map<Player, Integer> playersProfit;
    private final int dealerProfit;

    public Profit(Map<Player, Integer> playersProfit, int dealerProfit) {
        this.playersProfit = playersProfit;
        this.dealerProfit = dealerProfit;
    }

    public Map<Player, Integer> getPlayersProfit() {
        return playersProfit;
    }

    public int getDealerProfit() {
        return dealerProfit;
    }
}
