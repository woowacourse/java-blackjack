package blackjack.domain.participant;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.state.InitialState;
import blackjack.domain.participant.state.State;

public abstract class Participant {

    protected State state;

    protected Participant(final Deck deck) {
        final Card first = deck.drawCard();
        final Card second = deck.drawCard();
        this.state = InitialState.initiallyDrawCards(first, second);
    }

    public boolean isPossibleToDrawCard() {
        return state.isPossibleToDrawCard();
    }

    public List<Card> getCards() {
        return state.getCards();
    }

    public int getScore() {
        return state.getScore();
    }

    public State getState() {
        return state;
    }

}
