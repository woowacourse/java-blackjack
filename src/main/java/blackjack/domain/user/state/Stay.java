package blackjack.domain.user.state;

import blackjack.domain.card.Score;
import blackjack.domain.user.Hand;

public final class Stay extends Finished {

    protected Stay(Hand hand) {
        super(hand);
    }

    @Override
    public EarningRate calculateEarningRate(State opponentState) {
        Score opponentScore = opponentState.getScore();

        if (opponentState.hand.isBust() || this.getScore().isGreaterThan(opponentScore)) {
            return EarningRate.WIN;
        }
        if (this.getScore().equals(opponentScore)) {
            return EarningRate.TIE;
        }
        return EarningRate.LOSS;
    }
}
