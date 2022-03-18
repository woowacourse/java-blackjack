package blackjack.domain.participant;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.state.InitialState;
import blackjack.domain.participant.state.PlayState;

public abstract class Participant {

    protected PlayState state;

    protected Participant(final Deck deck) {
        this.state = InitialState.initializeState(deck);
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

    public PlayState getState() {
        return state;
    }

    public int getBettingAmount() {
        return state.getBettingAmount();
    }

}
