package domain.participant;

import domain.card.Card;
import domain.card.Hand;

public abstract class Participant {

    protected final Hand hand;

    protected Participant() {
        this.hand = new Hand();
    }

    public void hit(Card card) {
        hand.drawCard(card);
    }

    public Hand getHand() {
        return hand;
    }
}
