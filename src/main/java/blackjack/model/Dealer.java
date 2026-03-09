package blackjack.model;

import java.util.List;

public class Dealer {

    private static final int DRAW_UPPER_BOUND = 16;

    private final Hand hand;

    public Dealer() {
        this.hand = new Hand();
    }

    public boolean shouldDraw() {
        return hand.calculateScore() <= DRAW_UPPER_BOUND;
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public Hand getHand() {
        return hand;
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public int getScore() {
        return hand.calculateScore();
    }
}
