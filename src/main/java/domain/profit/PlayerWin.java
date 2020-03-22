package domain.profit;

import domain.CardCalculator;
import domain.player.Dealer;
import domain.player.Player;

public class PlayerWin implements ProfitStrategy {
    @Override
    public double getProfit(int bettingMoney) {
        return bettingMoney;
    }

    @Override
    public boolean condition(Player player, Dealer dealer) {
        return CardCalculator.determineWinner(player.getCard(), dealer.getCard());
    }
}
