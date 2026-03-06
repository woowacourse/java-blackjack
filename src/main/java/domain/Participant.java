package domain;

import java.util.List;

public class Participant {
    private final String name;
    private final HandCards handCards;
    private final boolean isDealer;

    public Participant(String name, HandCards handCards, boolean isDealer) {
        this.name = name;
        this.handCards = handCards;
        this.isDealer = isDealer;
    }

    public void addHandCard(Card card) {
        handCards.addCard(card);
    }

    public int getScore() {
        return handCards.getScore();
    }

    public boolean isBlackjack() {
        return handCards.isBlackjack();
    }

    public boolean isBust() {
        return handCards.isBust();
    }

    public String getName() {
        return name;
    }

    public List<Card> getHandCards() {
        return handCards.getHandCards();
    }

    public boolean isDealer() {
        return isDealer;
    }
}
