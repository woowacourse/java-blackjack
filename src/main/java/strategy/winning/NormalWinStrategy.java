package strategy.winning;

import constant.WinningResult;
import participant.Dealer;
import participant.Player;
import strategy.profit.ProfitCalculator;
import strategy.profit.WinCalculator;

public class NormalWinStrategy implements WinningStrategy {
    @Override
    public boolean matches(Player player, Dealer dealer) {
        return player.compareTo(dealer.sumCardNumbers()) == WinningResult.WIN;
    }

    @Override
    public ProfitCalculator getProfitCalculator() {
        return new WinCalculator();
    }
}
