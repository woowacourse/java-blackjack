package blackjack.model.participants;

import blackjack.model.cards.Card;
import blackjack.model.cards.Cards;
import blackjack.model.state.InitialState;
import blackjack.model.state.State;
import java.util.List;

public abstract class Participant {
    private State state;

    protected Participant() {
        this.state = new InitialState();
    }

    public abstract boolean canHit();

    public void addCard(Card card) {
        state = state.draw(card);
    }

    public void addCards(List<Card> cardToAdd) {
        state = state.drawCards(cardToAdd);
    }

    public Cards getCards() {
        return state.cards();
    }

    public int getScore() {
        return getCards().getScoreValue();
    }

    public void finishTurn() {
        state = state.stand();
    }

    public State getState() {
        return state;
    }
}
