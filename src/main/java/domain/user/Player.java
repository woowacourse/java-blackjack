package domain.user;

import domain.card.Card;
import domain.state.State;

public class Player {

    private final Participant participant;

    public Player(String PlayerName) {
        this.participant = new Participant(PlayerName);
    }

    public State hit(Card card) {
        return participant.hit(card);
    }

    public State stay() {
        return participant.stay();
    }

    public boolean equalsName(String name) {
        return participant.equalsName(name);
    }

    public Name getName() {
        return participant.getName();
    }

    public State getState() {
        return participant.getState();
    }
}
