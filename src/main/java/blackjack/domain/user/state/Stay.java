package blackjack.domain.user.state;

import blackjack.domain.card.Score;
import blackjack.domain.user.Hand;

public class Stay extends Finished {

    public Stay(Hand hand) {
        super(hand);
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public double calculateEarningRate(State opponentState) {
        Score opponentScore = opponentState.getScore();

        if (opponentState.hand.isBust() || this.getScore().isGreaterThan(opponentScore)) {
            return 1;
        }
        if (this.getScore().equals(opponentScore)) {
            return 0;
        }
        return -1;
    }
}
