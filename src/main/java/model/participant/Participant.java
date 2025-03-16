package model.participant;

import java.util.List;
import model.card.Card;
import model.card.CardHand;
import model.state.State;

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

    public void stay() {
        this.state = state.stay();
    }

    public CardHand getCardHand() {
        return state.cardHand();
    }

    public List<Card> getCards() {
        return getCardHand().getCards();
    }
}
