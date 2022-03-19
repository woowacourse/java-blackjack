package blackjack.domain.user.state;

import blackjack.domain.user.Hand;

public class Bust extends Finished {

    public Bust(Hand hand) {
        super(hand);
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public double calculateEarningRate(State opponentState) {
        return -1.0;
    }
}
