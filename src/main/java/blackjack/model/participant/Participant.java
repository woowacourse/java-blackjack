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

    public final void addCard(Card card) {
        state = state.hit(card);
    }

    public final BlackjackState getState() {
        return state;
    }

    public final Collection<Card> getCards() {
        return state.getCards();
    }

    public final int getScore() {
        return state.getScore();
    }

    public final boolean isPlaying() {
        return state.canHit();
    }
}
