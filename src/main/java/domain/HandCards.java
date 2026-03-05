package domain;

import java.util.List;

public class HandCards {
    private final List<Card> handCards;

    public HandCards(List<Card> handCards) {
        this.handCards = handCards;
    }

    public void addCard(Card card) {
        handCards.add(card);
    }

    public List<Card> getHandCards() {
        return List.copyOf(handCards);
    }
}
