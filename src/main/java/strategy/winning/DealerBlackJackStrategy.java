package strategy.winning;

import participant.Dealer;
import participant.Player;
import strategy.profit.LoseCalculator;
import strategy.profit.ProfitCalculator;

public class DealerBlackJackStrategy implements WinningStrategy {
    @Override
    public boolean matches(Player player, Dealer dealer) {
        return !player.isBlackJack() && dealer.isBlackJack();
    }

    @Override
    public ProfitCalculator getProfitCalculator() {
        return new LoseCalculator();
    }
}
