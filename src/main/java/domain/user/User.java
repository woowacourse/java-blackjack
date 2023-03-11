package domain.user;

import domain.card.Card;
import domain.user.state.Ready;
import domain.user.state.State;
import java.util.List;

public abstract class User {

    protected State state;

    public User() {
        this.state = new Ready();
    }

    public void hit(Card card) {
        this.state = state.draw(card);
    }

    public List<Card> getCards() {
        return state.getCards();
    }

    public abstract boolean isDrawable();

    public int getScore() {
        return state.getScore();
    }

    public State getState() {
        return state;
    }
}
