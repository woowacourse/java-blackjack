package blackjackgame.domain.blackjack;

import java.util.List;

public class GameProfit {
    private final double dealerProfit;
    private final List<Double> playersProfit;

    public GameProfit(double dealerProfit, List<Double> playersProfit) {
        this.dealerProfit = dealerProfit;
        this.playersProfit = playersProfit;
    }

    public double getDealerProfit() {
        return dealerProfit;
    }

    public List<Double> getPlayersProfit() {
        return playersProfit;
    }
}
