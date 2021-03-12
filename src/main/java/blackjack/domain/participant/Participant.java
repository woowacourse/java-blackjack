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

    public void setUpParticipantTwoCardsAndState() {
        state = StateFactory.createInitialState(Deck.draw(), Deck.draw());
    }

    public String getCurrentCardsInfo() {
        return state.cards().getCardsInfoToString();
    }

    public boolean isDrawable() {
        return !state.isFinished();
    }

    public void addCard(Card card) {
        state = state.addCard(card);
    }

    public int getCardsScore() {
        return state.cards().calculateCardsScore();
    }

    public String getName() {
        return name.getName();
    }

    public void stay() {
        state = state.stay();
    }
}
