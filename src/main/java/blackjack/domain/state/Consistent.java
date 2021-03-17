package blackjack.domain.state;

import blackjack.domain.card.UserDeck;

public abstract class Consistent extends BasicState {

    public Consistent(UserDeck userDeck) {
        super(userDeck);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public State stay() {
        return new Stay(super.getUserDeck());
    }

    @Override
    public double getProfitRate(State dealerState) {
        if ((dealerState instanceof BlackJack)
            || dealerState.getPoint() > super.getPoint()) {
            return -1 * NORMAL_RATE;
        }

        if (dealerState.getPoint() == super.getPoint()) {
            return ZERO_RATE;
        }

        return NORMAL_RATE;
    }
}
