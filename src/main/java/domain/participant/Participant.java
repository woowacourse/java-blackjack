package domain.participant;

import domain.card.Card;

import java.util.List;

public abstract class Participant {
    private final HandCards handCards;
    private final String name;

    public Participant(String name) {
        this.handCards = new HandCards();
        this.name = name;
    }

    public void receiveInitialCards(List<Card> firstHandCards) {
        handCards.receiveInitialCards(firstHandCards);
    }

    public void receiveHitCard(Card card) {
        handCards.receiveHitCard(card);
    }

    public boolean isBust() {
        return handCards.isBust();
    }

    public int calculateScore() {
        return handCards.calculateScore();
    }

    public List<Card> getHandCards() {
        return handCards.getCards();
    }

    public String getName() {
        return name;
    }
}
