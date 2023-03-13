package blackjack.domain.participant;

import blackjack.domain.Score;
import blackjack.domain.participant.dealer.Dealer;
import blackjack.domain.participant.player.Player;
import java.util.function.BiFunction;

public enum PlayerFinalState {
    BLACKJACK((player, dealer) -> {
        if (dealer.isBlackjack()) {
            return Constants.PLUS_100_PERCENT;
        }
        return Constants.PLUS_150_PERCENT;
    }),
    BUST((player, dealer) -> Constants.MINUS_100_PERCENT),
    STAY((player, dealer) -> {
        if (dealer.isBust() || player.calculateScore().isHigherThan(dealer.calculateScore())) {
            return Constants.PLUS_100_PERCENT;
        }
        return Constants.MINUS_100_PERCENT;
    });
    private final BiFunction<Player, Dealer, Double> earningRate;

    PlayerFinalState(BiFunction<Player, Dealer, Double> earningRate) {
        this.earningRate = earningRate;
    }

    public static double getEarningRateOf(Player player, Dealer dealer) {
        PlayerFinalState playerState = of(player);
        return playerState.earningRate.apply(player, dealer);
    }

    private static PlayerFinalState of(Participant participant) {
        Score score = participant.calculateScore();
        if (participant.showCards().size() == 2 && score.isBlackjack()) {
            return BLACKJACK;
        }
        if (participant.isBust()) {
            return BUST;
        }
        return STAY;
    }

    private static class Constants {
        public static final double MINUS_100_PERCENT = -1.0;
        public static final double PLUS_100_PERCENT = 1.0;
        public static final double PLUS_150_PERCENT = 1.5;
    }
}
