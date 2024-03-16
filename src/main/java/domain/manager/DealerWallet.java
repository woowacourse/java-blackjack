package domain.manager;

import domain.gamer.Player;
import java.util.Map;

public class DealerWallet {

    private Profit profit;

    public DealerWallet() {
        this.profit = new Profit(0);
    }

    public void calculateProfit(PlayersWallet playersWallet) {
        Map<Player, Profit> playersProfit = playersWallet.getPlayersProfit();
        double playersProfitSum = playersProfit.values().stream()
                .mapToDouble(Profit::getValue)
                .sum();
        profit = new Profit(-playersProfitSum);
    }

    public Profit getProfit() {
        return profit;
    }
}
