package domain.participant;

import domain.card.Card;
import domain.card.Hand;
import java.util.List;

public abstract class CardOwner {
    private final Hand ownedHand;

    protected CardOwner(Hand ownedHand) {
        this.ownedHand = ownedHand;
    }

    abstract boolean canReceive();

    public void receive(final Card card) {
        ownedHand.add(card);
    }

    public int calculateScore() {
        return ownedHand.calculateScore();
    }

    public int countCard() {
        return ownedHand.getSize();
    }

    public List<Card> getOwnedCards() {
        return ownedHand.getCards();
    }
}
