package domain.participant;

import domain.card.CardDeck;
import domain.card.Hand;

public class Participant {
    protected final Hand hand;

    public Participant() {
        this.hand = new Hand();
    }

    public void hitCard(final CardDeck standard) {
        hand.addCard(standard.hitCard());
    }

    public int sum() {
        return hand.sumWithAce();
    }

    public Hand getHand() {
        return hand;
    }
}
