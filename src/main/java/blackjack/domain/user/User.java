package blackjack.domain.user;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;
import blackjack.domain.strategy.hit.HitStrategy;
import blackjack.domain.user.state.State;

public abstract class User {

    protected State state;
    private final Name name;

    private User(State state, Name name) {
        this.state = state;
        this.name = name;
    }

    protected User(String input, State state) {
        this(state, new Name(input));
    }

    public boolean hit(Card card) {
        state = state.hit(card);
        return !isFinished();
    }

    public boolean stay() {
        state = state.stay();
        return !isFinished();
    }

    public boolean hitOrStay(Deck deck, HitStrategy strategy) {
        if (strategy.isHit()) {
            return hit(deck.drawCard());
        }
        return stay();
    }

    public boolean isBlackjack() {
        return state.isBlackjack();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public Score getScore() {
        return state.getScore();
    }

    public List<Card> getHandCards() {
        return state.getHandCards();
    }

    public String getName() {
        return name.getName();
    }

    public abstract List<Card> showInitCards();

    @Override
    public String toString() {
        return "User{" +
            "state=" + state +
            ", name=" + name +
            '}';
    }
}
