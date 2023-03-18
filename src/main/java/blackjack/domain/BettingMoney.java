package blackjack.domain;

import java.util.Map;

public class BettingMoney {

    private final Map<Player, Integer> bettingMoney;

    public BettingMoney(Map<Player, Integer> bettingMoney) {
        this.bettingMoney = bettingMoney;
    }

    public double getBlackjackProfit(Player player) {
        return bettingMoney.get(player) * 1.5;
    }

    public double getWinProfit(Player player) {
        return bettingMoney.get(player) * 1.0;
    }

    public double getDrawProfit(Player player) {
        return bettingMoney.get(player) * 0.0;
    }

    public double getLoseProfit(Player player) {
        return bettingMoney.get(player) * (-1.0);
    }
}
