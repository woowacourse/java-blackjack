package model.result;

import java.math.BigDecimal;
import dto.status.DealerStatus;
import dto.status.PlayerStatus;

public class ProfitCalculator {
    public static Integer calculateBetAmount(DealerStatus dealer, PlayerStatus player) {
        return calculateBustBetAmount(dealer, player).intValue();
    }

    private static BigDecimal calculateBustBetAmount(DealerStatus dealer, PlayerStatus player) {
        if(player.isBust()) {
            return BigDecimal.valueOf(-player.bet());
        }

        return calculateBlackJackBetAmount(dealer, player);
    }

    private static BigDecimal calculateBlackJackBetAmount(DealerStatus dealer, PlayerStatus player) {
        if(player.isBlackJack() && dealer.isBlackJack()) {
            return BigDecimal.ZERO;
        }

        if(player.isBlackJack()) {
            return BigDecimal.valueOf(player.bet()).multiply(BigDecimal.valueOf(1.5));
        }

        return calculateRegularBetAmount(dealer, player);
    }

    private static BigDecimal calculateRegularBetAmount(DealerStatus dealer, PlayerStatus player) {
        if(player.score() > dealer.score() ) {
            return BigDecimal.valueOf(player.bet());
        }

        if(player.score() < dealer.score()) {
            return BigDecimal.valueOf(-player.bet());
        }

        return BigDecimal.ZERO;
    }
}
