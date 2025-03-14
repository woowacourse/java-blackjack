package participant;

import java.util.List;
import card.Card;
import state.State;

public abstract class Participant {

    public static final int PARTICIPANT_MAX_NUMBER_FOR_BUST = 21;

    protected final State state;

    public Participant(final State state) {
        this.state = state;
    }

    public void hit(Card card) {
        state.draw(card);
    }

    public void receiveCards(List<Card> cards) {
        for (Card card : cards) {
            state.draw(card);
        }
    }

    public boolean isBust() {
        return state.isFinished();
    }

    public int score() {
        return state.cards().calculateScore();
    }

    public abstract boolean canReceiveCard();
}
