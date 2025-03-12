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

    public boolean resolveBust() {
        if (isHandBust() && containsOriginalAce()) {
            setOriginalAceValueToOne();
            resolveBust();
        }
        return !isHandBust();
    }

    public void setHandTotalToZero() {
        hand.setAllCardValueToZero();
    }

    protected boolean isHandBust() {
        return hand.isBust();
    }

    private boolean containsOriginalAce() {
        return hand.containsOriginalAce();
    }

    private void setOriginalAceValueToOne() {
        hand.setOriginalAceValueToOne();
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }
}
