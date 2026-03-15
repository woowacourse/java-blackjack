package domain.participant;

import domain.card.Cards;
import domain.card.Hand;
import domain.state.HandState;
import domain.card.Score;

public abstract class Participant {
    private final Hand hand;

    protected Participant() {
        this.hand = new Hand();
    }

    public void drawCard(Cards cards) {
        hand.addCard(cards.draw());
    }

    public Hand getCardList() {
        return hand;
    }

    public Score getScore() {
        return hand.getScore();
    }

    public int getResult() {
        return hand.getResult();
    }

    public boolean checkBust() {
        return hand.checkBust();
    }

    public HandState getHandState(){
        return hand.getHandState();
    }
}
