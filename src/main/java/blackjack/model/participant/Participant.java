package blackjack.model.participant;

import blackjack.model.card.Card;
import blackjack.model.card.Hand;
import java.util.List;

public abstract class Participant {

    protected final Hand hand;

    protected Participant() {
        this.hand = new Hand();
    }

    protected Participant(Hand hand) {
        this.hand = hand;
    }

    public void addCard(Card card) {
        hand.hit(card);
    }

    public Hand getHand() {
        return hand;
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public int getScore() {
        return hand.calculateScore();
    }

    public boolean isBust() {
        return hand.isBust();
    }
}
