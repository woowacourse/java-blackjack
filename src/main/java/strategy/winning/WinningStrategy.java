package strategy.winning;

import participant.Dealer;
import participant.Player;
import strategy.profit.ProfitCalculator;

public interface WinningStrategy {

    boolean matches(Player player, Dealer dealer);

    ProfitCalculator getProfitCalculator();
}
