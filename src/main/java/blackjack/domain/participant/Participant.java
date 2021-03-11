package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.state.State;
import blackjack.domain.state.StateFactory;

public abstract class Participant {
    protected State state;
    protected final Name name;

    public Participant(String name) {
        this.name = new Name(name);
    }

    public void setUpTwoCardsAndState() {
        state = StateFactory.createInitialState(Deck.draw(), Deck.draw());
    }

    public String getName() {
        return name.getName();
    }

    public String getCurrentCardsInfo() {
        return state.cards().getCardsInfoToString();
    }
}
