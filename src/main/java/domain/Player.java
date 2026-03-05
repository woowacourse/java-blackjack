package domain;

import java.util.List;

public class Player {
    private final String name;
    private final HandCards handCards;

    public Player(String name, HandCards handCards) {
        this.name = name;
        this.handCards = handCards;
    }

    public void addHandCard(Card card) {
        handCards.addCard(card);
    }

    public List<Card> getHandCards() {
        return handCards.getHandCards();
    }
}
