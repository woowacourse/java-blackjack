package blackjack.domain.participant;

import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.state.State;
import java.util.List;

public abstract class Participant {

    protected State state;

    public Participant(State state) {
        this.state = state;
    }

    public abstract boolean canHit();
    public abstract List<Card> showStartCards();

    public abstract String getName();

    public void finishTurn() {
        if(state.isHit()) {
            state = state.stand();
        }
    }

    public void startGame(Card card1, Card card2) {
        this.state = state.drawInitialCards(card1, card2);
    }

    public void hit(Card card) {
        this.state = state.draw(card);
    }

    public Score getScore() {
        return state.getScore();
    }

    public List<Card> getCardDeck() {
        return state.getCards();
    }

    public int getCardSize() {
        return state.getCards().size();
    }

    public State getState() {
        return state;
    }
}
