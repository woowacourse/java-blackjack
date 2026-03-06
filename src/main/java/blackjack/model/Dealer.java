package blackjack.model;

import java.util.List;

public class Dealer {

    private final Hand hand;
    private final DealerDrawPolicy drawPolicy;

    public Dealer(AceAdjustPolicy aceAdjustPolicy, DealerDrawPolicy drawPolicy) {
        this.hand = new Hand(aceAdjustPolicy);
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
