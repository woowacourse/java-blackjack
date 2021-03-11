package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.UserDeck;
import blackjack.domain.state.Bust;
import blackjack.domain.state.State;

public abstract class User {

    private State state;

    public User(State state) {
        this.state = state;
    }

    public void draw(Card card) {
        this.state = state.draw(card);
    }

    public boolean isAvailableDraw() {
        return !state.isFinished();
    }

    public boolean isBurstCondition() {
        if (state instanceof Bust) {
            return true;
        }
        return false;
    }

    public UserDeck getUserDeck() {
        return state.getUserDeck();
    }

    public int getPoint() {
        return state.getUserDeck().score();
    }

}
