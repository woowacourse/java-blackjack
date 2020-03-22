package domain.profit;

import domain.player.Dealer;
import domain.player.Player;

public interface ProfitStrategy {
    public double getProfit(int bettingMoney);
    public boolean condition(Player player, Dealer dealer);
}
