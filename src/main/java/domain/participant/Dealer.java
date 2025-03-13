package domain.participant;

import domain.card.Card;
import domain.card.Hand;
import java.util.List;

public class Dealer {
    private final Hand ownedHand;

    private Dealer() {
        this.ownedHand = Hand.of();
    }

    public static Dealer of() {
        return new Dealer();
    }

    public void receive(Card card) {
        ownedHand.add(card);
    }

    public int getScore() {
        return ownedHand.calculateScore();
    }

    public int getCardCount() {
        return ownedHand.getSize();
    }

    public List<Card> getOwnedCards() {
        return ownedHand.getCards();
    }
}
