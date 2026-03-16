package domain;

import java.util.List;

public class Player {

    private final Hand hand = new Hand();
    private final String name;

    private Player(String name) {
        this.name = name;
    }

    public static Player of(String name) {
        return new Player(name);
    }

    public void addInitialCards(List<Card> cards) {
        hand.addInitialCards(cards);
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public int calculateScore() {
        return hand.calculateScore();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public String getName() {
        return name;
    }
}
