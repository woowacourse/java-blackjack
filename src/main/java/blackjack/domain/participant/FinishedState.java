package blackjack.domain.participant;

import blackjack.domain.participant.dealer.Dealer;
import blackjack.domain.participant.player.Player;
import java.util.function.BiFunction;

public enum FinishedState {
    BLACK_JACK((player, dealer) -> {
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

    private BiFunction<Player, Dealer, Double> earningRate;

    FinishedState(BiFunction<Player, Dealer, Double> earningRate) {
        this.earningRate = earningRate;
    }

    public double getEarningRate(Player player, Dealer dealer) {
        return earningRate.apply(player, dealer);
    }
}
