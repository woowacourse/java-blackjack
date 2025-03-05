package domain;

import java.util.List;

public class Player {
    private final String name;
    private final CardHand cardHand;

    public Player(String name, CardHand cardHand) {
        this.name = name;
        this.cardHand = cardHand;
    }

    public int calculateScore() {
        return cardHand.calculateScore();
    }

    public boolean isBust() {
        return cardHand.isBust();
    }

    public boolean isBlackJack() {
        return cardHand.isBlackJack();
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cardHand.getCards();
    }
}
