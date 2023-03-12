package blackjack.domain.participant;

import blackjack.domain.Score;
import blackjack.domain.participant.dealer.Dealer;
import blackjack.domain.participant.player.Player;
import java.util.function.BiFunction;

public enum PlayerFinalState {
    BLACKJACK((player, dealer) -> {
        if (dealer.isBlackjack()) {
            return 1.0;
        }
        return 1.5;
    }),
    BUST((player, dealer) -> -1.0),
    STAY((player, dealer) -> {
        if (dealer.isBust() || player.calculateScore().isHigherThan(dealer.calculateScore())) {
            return 1.0;
        }
        return -1.0;
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
}
