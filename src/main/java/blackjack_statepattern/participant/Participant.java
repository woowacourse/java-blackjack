package blackjack_statepattern.participant;

import blackjack_statepattern.card.Card;
import blackjack_statepattern.state.Ready;
import blackjack_statepattern.state.State;

public abstract class Participant {
    private final String name;
    protected State state;

    public Participant(String name) {
        this.name = name;
        this.state = new Ready();
    }

    public boolean isReady() {
        return state.isReady();
    }

    public void receiveCard(Card card) {
        state = state.draw(card);
    }

    public String getName() {
        return name;
    }
}
