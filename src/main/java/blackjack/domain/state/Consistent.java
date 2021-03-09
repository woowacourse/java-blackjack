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
}
