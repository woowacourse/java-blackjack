package domain;

import java.util.List;

public class Participant {
    private final Name name;
    private final HandCards handCards;
    private final boolean isDealer;

    public Participant(Name name, HandCards handCards, boolean isDealer) {
        this.name = name;
        this.handCards = handCards;
        this.isDealer = isDealer;
    }

    public void addHandCard(Card card) {
        handCards.addCard(card);
    }

    public boolean isDealer() {
        return isDealer;
    }

    public boolean isBust() {
        return handCards.isBust();
    }

    public boolean isBlackjack() {
        return handCards.isBlackjack();
    }


    public int getScore() {
        return handCards.getScore();
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getHandCards() {
        return handCards.getHandCards();
    }
}
