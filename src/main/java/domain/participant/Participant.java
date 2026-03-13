package domain.participant;

import domain.Hand;
import domain.card.Card;

public class Participant {

    protected Hand hand;

    public Participant() {
        hand = new Hand();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public boolean isBlackjackAtFirst() {
        return isBlackjack() && hand.size() == 2;
    }

    public Hand getHand() {
        return hand;
    }

    public int getScore() {
        return hand.getSum();
    }

    public void draw(Card card) {
        hand.addCard(card);
    }

}
