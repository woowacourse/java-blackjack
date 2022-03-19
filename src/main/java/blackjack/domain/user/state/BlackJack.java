package blackjack.domain.user.state;

import blackjack.domain.user.Hand;

public class BlackJack extends Finished {

    public BlackJack(Hand hand) {
        super(hand);
    }

    @Override
    public double calculateEarningRate(State opponentState) {
        if (opponentState.isBlackjack()) {
            return 1.0;
        }
        return 1.5;
    }

    @Override
    public boolean isBlackjack() {
        return true;
    }
}
