package domain;

import domain.card.CanAddCardThreshold;
import domain.card.Card;

public class User {
    private final Cards cards;

    public User(CanAddCardThreshold canAddCardThreshold) {
        cards = new Cards(canAddCardThreshold);
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public boolean canReceiveCard() {
        return cards.canAddCard();
    }
}
