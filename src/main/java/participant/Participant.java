package participant;

import card.Card;
import java.util.List;
import state.State;
import state.finished.Bust;

public abstract class Participant {

    public static final int PARTICIPANT_MAX_NUMBER_FOR_BUST = 21;

    protected State state;

    public Participant() {
    }

    public void hit(Card card) {
        this.state = state.draw(card);
    }

    public void receiveCards(List<Card> cards) {
        for (Card card : cards) {
            state.draw(card);
        }
    }

    public int score() {
        return state.cards().calculateScore();
    }

    public boolean isBust() {
        return state instanceof Bust;
    }

    public abstract boolean canReceiveCard();
}
