package domain.profit;

import domain.player.Dealer;
import domain.player.Player;

public class BothBlackJack implements ProfitStrategy {
    @Override
    public double getProfit(int bettingMoney) {
        return bettingMoney;
    }

    @Override
    public boolean condition(Player player, Dealer dealer) {
        return player.isBlackJack() && dealer.isBlackJack();
    }
}
