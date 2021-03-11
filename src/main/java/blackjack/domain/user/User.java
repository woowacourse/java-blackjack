package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.state.State;
import blackjack.domain.state.StateFactory;
import blackjack.domain.state.Stay;

import java.util.Objects;

public abstract class User {
    protected final Name name;
    protected State state;

    public User(String name) {
        this(new Name(name));
    }

    public User(Name name) {
        this.name = name;
    }

    public final void initializeCards(Cards cards) {
        state = StateFactory.generateStateByCards(cards);
    }

    public final boolean isAbleToHit() {
        return !state.isFinish();
    }

    public final Score score() {
        return state.cards().totalScore();
    }

    public final Cards cards() {
        return state.cards();
    }

    public final String getName() {
        return name.getName();
    }

    public State getState() {
        return state;
    }

    public void changeState(State state) {
        this.state = state;
    }

    @Override
    public final String toString() {
        return name.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(state.cards(), user.state.cards()) &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state.cards(), name);
    }

    public final void hit(Card card) {
        changeState(state.draw(card));
    }

    public final void stay() {
        changeState(new Stay(cards()));
    }
}
