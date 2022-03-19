package blackjack.domain.participant;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.participant.state.InitialState;
import blackjack.domain.participant.state.State;

public abstract class Participant {

    protected State state;

    protected Participant(final List<Card> cards) {
        this.state = InitialState.initiallyDrawCards(cards);
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
