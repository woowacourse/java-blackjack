package blackjack.model;

import java.util.List;

public class Dealer {

    private final Hand hand;
    private final DealerDrawPolicy drawPolicy;

    public Dealer(Hand hand, DealerDrawPolicy drawPolicy) {
        this.hand = hand;
        this.drawPolicy = drawPolicy;
    }

    public boolean shouldDraw() {
        return drawPolicy.shouldDraw(hand.calculateScore());
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public int getScore() {
        return hand.calculateScore();
    }
}
