package domain.profit;

import domain.CardCalculator;
import domain.player.Dealer;
import domain.player.Player;

public class PlayerLoose implements ProfitStrategy {
    private static final double LOOSE_RATE = -1d;

    @Override
    public double getProfit(int bettingMoney) {
        return (LOOSE_RATE * (double) bettingMoney);
    }

    @Override
    public boolean condition(Player player, Dealer dealer) {
        return !CardCalculator.determineWinner(player.getCard(), dealer.getCard());
    }
}
