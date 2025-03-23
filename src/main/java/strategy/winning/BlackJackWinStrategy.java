package strategy.winning;

import participant.Dealer;
import participant.Player;
import strategy.profit.BlackJackWinCalculator;
import strategy.profit.ProfitCalculator;

public class BlackJackWinStrategy implements WinningStrategy {
    @Override
    public boolean matches(Player player, Dealer dealer) {
        return player.isBlackJack() && !dealer.isBlackJack();
    }

    @Override
    public ProfitCalculator getProfitCalculator() {
        return new BlackJackWinCalculator();
    }
}
