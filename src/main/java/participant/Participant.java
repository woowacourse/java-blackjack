package participant;

import card.Card;
import card.GameScore;
import java.util.List;
import state.State;

public abstract class Participant {
    protected State state;

    public Participant(final State state) {
        this.state = state;
    }

    public abstract List<Card> openInitialCard();

    public abstract Name getName();

    public boolean isRunning() {
        return !state.isFinished();
    }

    public void receiveCard(Card newCard) {
        this.state = state.receiveCard(newCard);
    }

    public List<Card> getCards() {
        return state.cardHand().getCards();
    }

    public GameScore getScore() {
        return state.calculateScore();
    }
}
