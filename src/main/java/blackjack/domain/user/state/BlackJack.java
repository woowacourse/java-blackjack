package blackjack.domain.user.state;

import blackjack.domain.user.Hand;

public final class BlackJack extends Finished {

    protected BlackJack(Hand hand) {
        super(hand);
    }

    @Override
    public EarningRate calculateEarningRate(State opponentState) {
        if (opponentState.isBlackjack()) {
            return EarningRate.WIN;
        }
        return EarningRate.BLACKJACK_WIN;
    }
}
