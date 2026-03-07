package domain.participant;

import domain.card.Card;
import domain.card.Hand;

public abstract class Participant {

    protected final Hand hand;

    protected Participant() {
        this.hand = new Hand();
    }

    public void receive(Card card) {
        hand.drawCard(card);
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }

    public int getScore() {
        return hand.calculateScore();
    }

    public Hand getHand() {
        return hand;
    }
}
