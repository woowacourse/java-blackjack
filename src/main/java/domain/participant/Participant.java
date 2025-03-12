package domain.participant;

import domain.card.Card;
import domain.card.Hand;

public class Participant {
    private final Hand hand;

    public Participant(Hand hand) {
        this.hand = hand;
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public Hand getHand() {
        return hand;
    }
}
