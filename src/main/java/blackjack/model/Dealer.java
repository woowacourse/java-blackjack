package blackjack.model;

import java.util.List;

public class Dealer {

    private final Hand hand;
    private final DealerHitPolicy hitPolicy;

    public Dealer(Hand hand, DealerHitPolicy hitPolicy) {
        this.hand = hand;
        this.hitPolicy = hitPolicy;
    }

    public boolean shouldHit() {
        return hitPolicy.shouldHit(hand.calculateScore());
    }

    public void hit(Card card) {
        hand.addCard(card);
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public int getScore() {
        return hand.calculateScore();
    }
}
