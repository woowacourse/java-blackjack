package domain.user;

import domain.card.Card;
import domain.state.State;

public class Dealer {

    private final Participant participant;

    public Dealer(String dealerName) {
        this.participant = new Participant(dealerName);
    }

    public State hit(Card card) {
        return participant.hit(card);
    }

    public State stay() {
        return participant.stay();
    }

    public Name getName() {
        return participant.getName();
    }

    public State getState() {
        return participant.getState();
    }
}
