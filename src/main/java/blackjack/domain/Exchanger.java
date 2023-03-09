package blackjack.domain;

import java.util.List;
import java.util.Map;

public class Exchanger {

    private final Map<String, Integer> bettingMoney;

    public Exchanger(Map<String, Integer> bettingMoney) {
        this.bettingMoney = bettingMoney;
    }

    public double calculatePlayerProfit(String name, GameResult gameResult) {
        if (gameResult.equals(GameResult.BLACKJACK)) {
            return bettingMoney.get(name) * 1.5;
        }
        if (gameResult.equals(GameResult.WIN)) {
            return bettingMoney.get(name);
        }
        if (gameResult.equals(GameResult.DRAW)) {
            return 0;
        }
        return bettingMoney.get(name) * (-1.0);
    }

    public double calculateDealerProfit(List<Double> playersProfit) {
        double playersProfitSum = playersProfit.stream()
                .mapToDouble(profit -> profit).sum();
        return playersProfitSum * (-1);
    }
}
