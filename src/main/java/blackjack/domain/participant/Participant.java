package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;
import blackjack.domain.state.Hit;
import blackjack.domain.state.State;

public abstract class Participant {
    private final Name name;
    private State state;

    public Participant(final String name) {
        this.name = new Name(name);
        this.state = new Hit(new CardHand());
    }

    public abstract boolean canReceiveCard();

    public void receiveCard(final Card card) {
        state = state.draw(card);
    }

    public int calculateScore() {
        return state.calculateScore();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public void stay() {
        state = state.stay();
    }
}
