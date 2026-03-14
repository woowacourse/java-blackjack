package blackjack.model.participant;

import blackjack.model.card.Card;
import blackjack.model.state.BlackjackState;
import java.util.Collection;

public abstract class Participant {

    protected BlackjackState state;

    protected Participant() {
        this.state = BlackjackState.init();
    }

    protected Participant(BlackjackState state) {
        this.state = state;
    }

    public void addCard(Card card) {
        state = state.hit(card);
    }

    public BlackjackState getState() {
        return state;
    }

    public Collection<Card> getCards() {
        return state.getCards();
    }

    public int getScore() {
        return state.getScore();
    }

    public boolean isPlaying() {
        return state.canHit();
    }
}
