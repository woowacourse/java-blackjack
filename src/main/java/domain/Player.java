package domain;

import java.util.List;

public class Player {
    private final String name;
    private final HandCards handCards;
    private final boolean isDealer;

    public Player(String name, HandCards handCards, boolean isDealer) {
        this.name = name;
        this.handCards = handCards;
        this.isDealer = isDealer;
    }

    public void addHandCard(Card card) {
        handCards.addCard(card);
    }

    public List<Card> getHandCards() {
        return handCards.getHandCards();
    }
}
