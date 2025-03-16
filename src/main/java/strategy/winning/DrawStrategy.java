package strategy.winning;

import participant.Dealer;
import participant.Player;
import strategy.profit.DrawCalculator;
import strategy.profit.ProfitCalculator;

public class DrawStrategy implements WinningStrategy {
    @Override
    public boolean matches(Player player, Dealer dealer) {
        return player.sumCardNumbers() == dealer.sumCardNumbers();
    }

    @Override
    public ProfitCalculator getProfitCalculator() {
        return new DrawCalculator();
    }
}
