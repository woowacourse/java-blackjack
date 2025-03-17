package blackjack.domain.participant;

import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.state.HandState;
import java.util.List;

public abstract class Participant {

    protected HandState handState;

    public Participant(HandState handState) {
        this.handState = handState;
    }

    public abstract boolean canHit();
    public abstract List<Card> showStartCards();

    public abstract String getName();

    public void finishTurn() {
        if(!handState.isFinished()) {
            handState = handState.stand();
        }
    }

    public void startGame(Card card1, Card card2) {
        this.handState = handState.drawInitialCards(card1, card2);
    }

    public void hit(Card card) {
        this.handState = handState.draw(card);
    }

    public Score getScore() {
        return handState.getScore();
    }

    public List<Card> getCardDeck() {
        return handState.getCards();
    }

    public int getCardSize() {
        return handState.getCards().size();
    }

    public HandState getState() {
        return handState;
    }
}
