package domain.game;

import domain.participant.Dealer;
import domain.participant.Player;

import java.math.BigDecimal;

public class ProfitCalculator {

    public static BigDecimal calculatePlayerProfit(Player player, Dealer dealer) {
        WinningStatus winningStatus = WinningStatus.of(player, dealer);
        BigDecimal betAmount = player.betAmount();

        if (player.isBlackjack() && winningStatus == WinningStatus.WIN) {
            return betAmount.multiply(BigDecimal.valueOf(1.5));
        }

        if (winningStatus == WinningStatus.WIN) {
            return betAmount;
        }

        if (winningStatus == WinningStatus.TIE) {
            return BigDecimal.ZERO;
        }

        return betAmount.negate();
    }
}
