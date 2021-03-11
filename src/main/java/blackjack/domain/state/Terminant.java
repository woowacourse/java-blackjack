package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.UserDeck;

public abstract class Terminant extends BasicState {

    public Terminant(UserDeck userDeck) {
        super(userDeck);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public State draw(Card card) {
        return this;
    }

    @Override
    public State stay() {
        return new Stay(super.getUserDeck());
    }
}
