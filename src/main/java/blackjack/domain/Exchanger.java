package blackjack.domain;

import java.util.List;

public class Exchanger {

    private final BettingMoney bettingMoney;

    public Exchanger(BettingMoney bettingMoney) {
        this.bettingMoney = bettingMoney;
    }

    public double calculatePlayerProfit(Person player, GameResult gameResult) {
        if (gameResult == GameResult.BLACKJACK) {
            return bettingMoney.getBlackjackProfit((Player) player);
        }
        if (gameResult == GameResult.WIN) {
            return bettingMoney.getWinProfit((Player) player);
        }
        if (gameResult == GameResult.DRAW) {
            return bettingMoney.getDrawProfit((Player) player);
        }
        return bettingMoney.getLoseProfit((Player) player);
    }

    public double calculateDealerProfit(List<Double> playersProfit) {
        double playersProfitSum = playersProfit.stream()
                .mapToDouble(profit -> profit).sum();
        return playersProfitSum * (-1);
    }
}
