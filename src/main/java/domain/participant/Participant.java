package domain.participant;

import domain.card.Card;
import domain.card.Hand;
import java.util.List;

public class Participant {

    protected final Hand hand;

    public Participant() {
        this.hand = new Hand();
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public int calculateScore() {
        return hand.calculateTotalScore();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public List<Card> getHand() {
        return hand.getCard();
    }
}
