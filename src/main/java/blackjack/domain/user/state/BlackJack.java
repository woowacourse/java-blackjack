package blackjack.domain.user.state;

import blackjack.domain.user.Hand;

public class BlackJack extends Finished {

    public BlackJack(Hand hand) {
        super(hand);
    }

    @Override
    public EarningRate calculateEarningRate(State opponentState) {
        if (opponentState.isBlackjack()) {
            return EarningRate.WIN;
        }
        return EarningRate.BLACKJACK_WIN;
    }

    @Override
    public boolean isBlackjack() {
        return true;
    }
}
