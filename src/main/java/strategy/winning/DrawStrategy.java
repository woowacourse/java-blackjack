package strategy.winning;

import constant.WinningResult;
import participant.Dealer;
import participant.Player;
import strategy.profit.DrawCalculator;
import strategy.profit.ProfitCalculator;

public class DrawStrategy implements WinningStrategy {
    @Override
    public boolean matches(Player player, Dealer dealer) {
        return player.compareTo(dealer.sumCardNumbers()) == WinningResult.DRAW;
    }

    @Override
    public ProfitCalculator getProfitCalculator() {
        return new DrawCalculator();
    }
}
