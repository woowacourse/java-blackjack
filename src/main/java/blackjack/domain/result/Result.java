package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Guest;

public enum Result {

    BLACKJACK(1.5),
    WIN(1),
    DRAW(0),
    LOSE(-1),
    ;

    private final double profitRatio;

    Result(final double profitRatio) {
        this.profitRatio = profitRatio;
    }

    static Result decide(final Dealer dealer, final Guest guest) {
        if (guest.isBlackjack() && dealer.isNotBlackjack()) {
            return BLACKJACK;
        }
        if (guest.isBust() || (dealer.isNotBust() && dealer.getTotalScore() > guest.getTotalScore())) {
            return LOSE;
        }
        if (dealer.isBust() || guest.getTotalScore() > dealer.getTotalScore()) {
            return WIN;
        }
        return DRAW;
    }

    double getProfitRatio() {
        return profitRatio;
    }
}
