package blackjack.model.participant;

import blackjack.model.deck.Card;
import blackjack.model.state.playing.Hit;
import blackjack.model.state.State;
import java.util.List;

public abstract class Participant {

    protected State state;

    public Participant() {
        this.state = new Hit();
    }

    public void receiveInitialCards(final List<Card> cards) {
        for (Card card : cards) {
            state = state.draw(card);
        }
    }

    public void draw(final Card card) {
        state = state.draw(card);
    }

    public int notifyScore() {
        return state.calculateScore();
    }

    public List<Card> openCards() {
        return state.hand().getCards();
    }

    public abstract boolean canHit();

    public boolean isBust() {
        return state.hand().isBust();
    }

    public boolean hasManyCardsThan(Participant other) {
        return state.hand().hasManyThan(other.state.hand());
    }

    public boolean hasSameCardsSizeThan(Participant other) {
        return state.hand().hasSameSizeWith(other.state.hand());
    }
}
