package domain.profit;

import domain.player.Dealer;
import domain.player.Player;

public class PlayerBlackJack implements ProfitStrategy {
    private static final double WIN_BY_BLACK_JACK_RATE = 1.5d;

    @Override
    public double getProfit(int bettingMoney) {
        return (WIN_BY_BLACK_JACK_RATE * (double) bettingMoney);
    }

    @Override
    public boolean condition(Player player, Dealer dealer) {
        return player.isBlackJack();
    }
}
