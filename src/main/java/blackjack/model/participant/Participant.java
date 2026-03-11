package blackjack.model.participant;

import blackjack.model.card.Card;
import blackjack.model.hand.Hand;
import blackjack.model.hand.UninitializedHand;
import java.util.Collection;

public abstract class Participant {

    protected Hand hand;

    protected Participant() {
        this.hand = new UninitializedHand();
    }

    protected Participant(Hand hand) {
        this.hand = hand;
    }

    public void addCard(Card card) {
        hand = hand.hit(card);
    }

    public Hand getHand() {
        return hand;
    }

    public Collection<Card> getCards() {
        return hand.getCards();
    }

    public int getScore() {
        return hand.calculateScore();
    }

    public boolean isPlaying() {
        return hand.canHit();
    }
}
