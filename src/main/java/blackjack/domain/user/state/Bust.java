package blackjack.domain.user.state;

import blackjack.domain.user.Hand;

public final class Bust extends Finished {

    protected Bust(Hand hand) {
        super(hand);
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public EarningRate calculateEarningRate(State opponentState) {
        return EarningRate.LOSS;
    }
}
