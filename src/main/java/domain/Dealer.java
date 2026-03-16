package domain;

import java.util.List;

public class Dealer {

    private static final int DEALER_HIT_THRESHOLD = 16;

    private final Hand hand = new Hand();

    private Dealer(List<Card> cards) {
        hand.addInitialCards(cards);
    }

    public static Dealer of(List<Card> cards) {
        return new Dealer(cards);
    }

    public boolean isHit() {
        return hand.calculateScore() <= DEALER_HIT_THRESHOLD;
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
}