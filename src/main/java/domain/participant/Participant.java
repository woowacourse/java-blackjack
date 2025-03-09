package domain.participant;

import domain.card.Card;
import domain.card.Hand;

public abstract class Participant {

    private final String name;
    private final Hand hand;

    public Participant(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public int getHandTotal() {
        return hand.getTotal();
    }

    public boolean isHandBust() {
        return hand.isBust();
    }

    public boolean containsOriginalAce() {
        return hand.containsOriginalAce();
    }

    public void setOriginalAceValueToOne() {
        hand.setOriginalAceValueToOne();
    }

    public void setHandTotalToZero() {
        hand.setAllCardValueToZero();
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }
}
