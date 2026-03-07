package blackjack.model;

import java.util.List;

public class Dealer {

    private final Hand hand;

    public Dealer(Hand hand) {
        this.hand = hand;
    }

    public boolean shouldHit(DealerHitPolicy dealerHitPolicy) {
        return dealerHitPolicy.shouldHit(hand.calculateScore());
    }

    public void hit(Card card) {
        hand.addCard(card);
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public Score getScore() {
        return hand.calculateScore();
    }
}
