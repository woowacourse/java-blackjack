package strategy.winning;

import constant.WinningResult;
import participant.Dealer;
import participant.Player;
import strategy.profit.LoseCalculator;
import strategy.profit.ProfitCalculator;

public class LoseStrategy implements WinningStrategy {
    @Override
    public boolean matches(Player player, Dealer dealer) {
        return player.compareTo(dealer.sumCardNumbers()) == WinningResult.LOSE;
    }

    @Override
    public ProfitCalculator getProfitCalculator() {
        return new LoseCalculator();
    }
}
