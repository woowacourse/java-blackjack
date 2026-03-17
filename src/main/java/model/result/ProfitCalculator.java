package model.result;

import java.math.BigDecimal;
import model.participant.DealerStatus;
import model.participant.PlayerStatus;

public class ProfitCalculator {
    private static final Double BLACK_JACK_BAT_RATE = 1.5;

    public static Long calculateBetAmount(DealerStatus dealer, PlayerStatus player) {
        return calculateBustBetAmount(dealer, player).longValue();
    }

    private static BigDecimal calculateBustBetAmount(DealerStatus dealer, PlayerStatus player) {
        if (player.isBust()) {
            return BigDecimal.valueOf(-player.bet());
        }

        return calculateBlackJackBetAmount(dealer, player);
    }

    private static BigDecimal calculateBlackJackBetAmount(DealerStatus dealer, PlayerStatus player) {
        if (player.isBlackJack() && dealer.isBlackJack()) {
            return BigDecimal.ZERO;
        }

        if (player.isBlackJack()) {
            return BigDecimal.valueOf(player.bet()).multiply(BigDecimal.valueOf(BLACK_JACK_BAT_RATE));
        }

        return calculateRegularBetAmount(dealer, player);
    }

    private static BigDecimal calculateRegularBetAmount(DealerStatus dealer, PlayerStatus player) {
        if (player.score() > dealer.score() || dealer.isBust()) {
            return BigDecimal.valueOf(player.bet());
        }

        if (player.score() < dealer.score()) {
            return BigDecimal.valueOf(-player.bet());
        }

        return BigDecimal.ZERO;
    }
}
